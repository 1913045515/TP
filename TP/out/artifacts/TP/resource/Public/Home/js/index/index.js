$(document).ready(function(){
    var video = document.getElementById('video');
    $("#change-status").click(function() {
        // ������ͣ
        if(video.paused == true) {
            video.play()
            $("#pause-icon").css('display', 'block')
            $("#play-icon").css('display', 'none')
        } else {
            video.pause()
            $("#pause-icon").css('display', 'none')
            $("#play-icon").css('display', 'block')
        }
    })
});