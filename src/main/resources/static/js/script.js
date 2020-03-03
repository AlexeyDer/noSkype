var isGetListUsers = false;

function writeTextInSearchBox(data) {
    $('searchUsername').val(data);
}

function getListUsers() {
    if (!isGetListUsers) {
        $.getJSON("/api/user/list").then(function (data) {
            for (var i = 0; i < data.length; i++) {
                $('#users')
                    .append('<button onclick = "writeTextInSearchBox(data[i].username)" type="submit">' + data[i].username + '</button>');
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
console.info("Get name user");

if (!location.hash) {
    location.hash = name.toString();
}
const roomHash = location.hash;
console.info("Create roomHash user");


const drone = new ScaleDrone('yiS12Ts5RdNhebyM');
const roomName = 'observable-' + roomHash;
var configuration = null;
let room;
let peerConnection;
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
        console.log('MEMBERS', members);
        const isOfferer = members.length === 2;
        initialize(isOfferer);
    });
});

function send(message) {
    drone.publish({
        room: roomName,
        message
    });
}

var input = document.getElementById("messageInput");

function initialize(isOfferer) {

    peerConnection = new RTCPeerConnection(configuration, {
        optional: [{
            RtpDataChannels: true
        }]
    });

    // Setup ice handling
    peerConnection.onicecandidate = event => {
        if (event.candidate) {
            send({'candidate': event.candidate});
        }
    };

    dataChannel = peerConnection.createDataChannel("dataChannel", {
        reliable: true
    });


    if (isOfferer) {
        peerConnection.onnegotiationneeded = () => {
            peerConnection.createOffer().then(localDescCreated).catch(onError);
        }
    }

    // display remote stream in #remoteVideo
    peerConnection.ontrack = event => {
        const stream = event.streams[0];
        if (!remoteVideo.srcObject || remoteVideo.srcObject.id !== stream.id) {
            remoteVideo.srcObject = stream;
        }
    };
    /*
            navigator.mediaDevices.getUserMedia({
              audio: true,
              video: true,
            }).then(stream => {
              // show your local video #localVideo
              localVideo.srcObject = stream;
              // add your thread to the peer
              stream.getTracks().forEach(track => peerConnection.addTrack(track, stream));
            }, onError);
    */
    room.on('data', (message, client) => {
        if (client.id === drone.clientId) {
            return;
        }

        if (message.sdp) {
            // called after receiving a proposal or response from another user
            peerConnection.setRemoteDescription(new RTCSessionDescription(message.sdp), () => {
                // upon receipt of the offer, we respond to it
                if (peerConnection.remoteDescription.type === 'offer') {
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


    dataChannel.onmessage = function (event) {
        insertMessageToDOM(JSON.parse(event.data), false)
    };

    dataChannel.onclose = function () {
        console.log("data channel is closed");
    };
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
        () => send({'sdp': peerConnection.localDescription}),
        onError
    );
}

//Modify
function insertMessageToController(options, isFromMe) {
    $.ajax({
        type: "POST",
        url: "/api/getMessage",
        data: {
            myMsg: {
                "name": options.name,
                "content": options.content,
                "emoji": options.emoji,
                "isFromMe": isFromMe
            }
        },
        success: function (response) {
            // do something ...
            console.log("insertMessageToController success!")
        },
        error: function (e) {
            alert('Error: ' + e);
        }
    });

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
    // insertMessageToController(data, true);
    console.info("Send data to output in html");

}