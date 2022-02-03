var isGetListUsers = false;

function writeTextInSearchBox(name) {
    console.log("Name: ", name);
    $('searchUsername').val(name);
}

function getListUsers() {
    if (!isGetListUsers) {
        $.getJSON("/api/user/list").then(function (data) {
            console.log('Data: ', typeof data);
            console.log('Data: ', data);

            for (let key in data) {
                if (data.hasOwnProperty(key)) {
                    console.log('key: ', data[key].username);
                    $('#users')
                        .append('<button onclick = writeTextInSearchBox(data[key].username) type="submit">' + data[key].username + '</button>');
                }
            }
        });
        isGetListUsers = true;
    }
}

const possibleEmojis = [
    '🐀', '🐁', '🐭', '🐹', '🐂', '🐃', '🐄', '🐮', '🐅', '🐆', '🐯', '🐇', '🐐', '🐑', '🐏', '🐴',
    '🐎', '🐱', '🐈', '🐰', '🐓', '🐔', '🐤', '🐣', '🐥', '🐦', '🐧', '🐘', '🐩', '🐕', '🐷', '🐖',
    '🐗', '🐫', '🐪', '🐶', '🐺', '🐻', '🐨', '🐼', '🐵', '🙈', '🙉', '🙊', '🐒', '🐉', '🐲', '🐊',
    '🐍', '🐢', '🐸', '🐋', '🐳', '🐬', '🐙', '🐟', '🐠', '🐡', '🐚', '🐌', '🐛', '🐜', '🐝', '🐞',
];

function randomEmoji() {
    var randomIndex = Math.floor(Math.random() * possibleEmojis.length);
    console.info("Create random emoji");
    return possibleEmojis[randomIndex];
}

let name = $("#name").html();
console.info("Get name user: ", name);

if (!location.hash) {
    location.hash = name.toString();
}
const roomHash = location.hash;
console.info("Create roomHash user");


const drone = new ScaleDrone('yiS12Ts5RdNhebyM');
const roomName = 'observable-' + roomHash;
var configuration = {
    iceServers: [{
        urls: 'stun:stun.l.google.com:19302' // Google's public STUN server
    }]
};
let room;
let peerConnection;
let dataChannel;
const emoji = randomEmoji();

function onSuccess() {
};

function onError(error) {
    console.error(error);
};

drone.on('open', error => {
    if (error) {
        return console.error(error);
    }
    room = drone.subscribe(roomName);
    room.on('open', error => {
        if (error) {
            onError(error);
        }
    });
    // list person
    // connect to room
    room.on('members', members => {
        if (members.length >= 3) {
            return alert('The room is full');
        }
        console.log('MEMBERS', members);
        const isOfferer = members.length === 2;
        initialize(isOfferer);
    });
});

function sendSignalingMessage(message) {
    drone.publish({
        room: roomName,
        message
    });
}

// function send(message) {
//     drone.publish({
//         room: roomName,
//         message
//     });
// }

var input = document.getElementById("messageInput");

function initialize(isOfferer) {

    console.log('Starting WebRTC in as', isOfferer ? 'offerer' : 'waiter');

    peerConnection = new RTCPeerConnection(configuration, {
        optional: [{
            RtpDataChannels: true
        }]
    });

    console.log('Setup ice handling');
    peerConnection.onicecandidate = event => {
        if (event.candidate) {
            sendSignalingMessage({'candidate': event.candidate});
            console.log('candidate: ', event.candidate);
        }
    };

    // dataChannel = peerConnection.createDataChannel("dataChannel", {
    //     reliable: true
    // });

    if (isOfferer) {
        peerConnection.onnegotiationneeded = () => {
            peerConnection.createOffer().then(localDescCreated).catch(onError);
        }
        dataChannel = peerConnection.createDataChannel('dataChannel');
        setupDataChannel();
    } else {
        peerConnection.ondatachannel = event => {
            dataChannel = event.channel;
            setupDataChannel();
        }
    }

    // display remote stream in #remoteVideo
    peerConnection.ontrack = event => {
        const stream = event.streams[0];
        if (!remoteVideo.srcObject || remoteVideo.srcObject.id !== stream.id) {
            remoteVideo.srcObject = stream;
        }
    };

    room.on('data', (message, client) => {
        if (client.id === drone.clientId) {
            return;
        }

        if (message.sdp) {
            // called after receiving a proposal or response from another user
            peerConnection.setRemoteDescription(new RTCSessionDescription(message.sdp), () => {
                console.log('peerConnection.remoteDescription.type', peerConnection.remoteDescription.type);

                // upon receipt of the offer, we respond to it
                if (peerConnection.remoteDescription.type === 'offer') {
                    console.log('Answering offer');
                    peerConnection.createAnswer().then(localDescCreated).catch(onError);
                }
            }, onError);
        } else if (message.candidate) {
            // add new ICE candidate to remote connection description
            peerConnection.addIceCandidate(
                new RTCIceCandidate(message.candidate), onSuccess, onError
            );
        }
    });

    // dataChannel.onmessage = function (event) {
    //     insertMessageToDOM(JSON.parse(event.data), false)
    // };
    //
    // dataChannel.onclose = function () {
    //     console.log("data channel is closed");
    // };
}

function insertMessageToDOM(options, isFromMe) {
    console.info("Output message data in html");
    const template = document.querySelector('template[data-template="message"]');
    const nameEl = template.content.querySelector('.message__name');

    if (options.name) {
        nameEl.innerText = options.emoji + ' ' + options.name;
    }
    console.info("Add User name and emoji to Message");
    template.content.querySelector('.message__bubble').innerText = options.content;
    console.info("Add content message");
    const clone = document.importNode(template.content, true);
    const messageEl = clone.querySelector('.message');

    if (isFromMe) {
        messageEl.classList.add('message--mine');
        console.info("Imaging my message");
    } else {
        messageEl.classList.add('message--theirs');
        console.info("Imaging opponent message");
    }
    const messagesEl = document.querySelector('.messages');
    messagesEl.appendChild(clone);

    // Scroll to bottom
    messagesEl.scrollTop = messagesEl.scrollHeight - messagesEl.clientHeight;
    console.log(options.name + ": " + options.content);
}

function createVideo() {
    navigator.mediaDevices.getUserMedia({
        audio: true,
        video: true,
    }).then(stream => {
        // show your local video #localVideo
        localVideo.srcObject = stream;
        // add your thread to the peer
        stream.getTracks().forEach(track => peerConnection.addTrack(track, stream));
    }, onError);
}

function localDescCreated(desc) {
    peerConnection.setLocalDescription(
        desc,
        () => sendSignalingMessage({'sdp': peerConnection.localDescription}),
        onError
    );
}

function setupDataChannel() {
    checkDataChannelState();
    dataChannel.onopen = checkDataChannelState;
    dataChannel.onclose = checkDataChannelState;
    dataChannel.onmessage = event =>
        insertMessageToDOM(JSON.parse(event.data), false)
}

function checkDataChannelState() {
    console.log('WebRTC channel state is:', dataChannel.readyState);
    if (dataChannel.readyState === 'open') {
        insertMessageToDOM({content: 'WebRTC data channel is now open'});
    }
}

function sendMessage() {
    console.log(name, ": ", input.value);

    const data = {
        name,
        content: input.value,
        emoji,
    };
    input.value = "";
    dataChannel.send(JSON.stringify(data));
    console.info("Send json data message to another client");

    insertMessageToDOM(data, true);
    console.info("Send data to output in html");

}

// insertMessageToDOM({content: 'Chat URL is ' + location.href});