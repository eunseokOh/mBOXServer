<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <script src="https://unpkg.com/video.js/dist/video.js"></script>
  <script src="https://unpkg.com/videojs-contrib-hls/dist/videojs-contrib-hls.js"></script>
  
</head>
<style>
.vjs-control-bar, .vjs-modal-dialog, .vjs-big-play-button{
	display: none;
}
</style>
<body>
<video id="example-video" controls preload="auto" width="640" height="268" 
  data-setup='{}'>
    <source src="https://player.vimeo.com/external/238721080.m3u8?s=b9216f84b8a67eedbf7905d34d9ffbb5be974353&oauth2_token_id=1004245140" type="application/x-mpegURL">
  </video>

<script>
var player = videojs('example-video');
player.play();
</script>
</body>
</html>