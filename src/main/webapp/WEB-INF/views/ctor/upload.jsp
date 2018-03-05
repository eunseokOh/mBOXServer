<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/resources/js/jquery-form/jquery-form.min.js"></script>
<style>
* {
	box-sizing: border-box;
}

.videoThumbnail {
	background: black;
	width: 100%;
	padding-bottom: 56.25%;
}

.col-1 {
	width: 8.33%;
}

.col-2 {
	width: 16.66%;
}

.col-3 {
	width: 25%;
}

.col-4 {
	width: 33.33%;
}

.col-5 {
	width: 41.66%;
}

.col-6 {
	width: 50%;
}

.col-7 {
	width: 58.33%;
}

.col-8 {
	width: 66.66%;
}

.col-9 {
	width: 75%;
}

.col-10 {
	width: 83.33%;
}

.col-11 {
	width: 91.66%;
}

.col-12 {
	width: 100%;
}

[class*="col-"] {
	float: left;
	padding: 5px;
}

.row {
	display: table;
	height: auto;
	width: 100%;
}

.tag_gb {
	font-size: 0.7rem;
}

.setting_input {
	width: 100%;
	padding: 11px 10px;
	border-radius: 5px;
	border: 1px solid #E3E8E9;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Video Upload</h1>
	<form method="post" enctype="multipart/form-data" id="myForm"
		action="/video/" onchange="uploadFile(event.target)">

		<input type="hidden" name="_method" value="PUT"> <input
			type="file" name="files" multiple id="files" /> <input type="submit"
			value="Upload" id="form_submit" style="visibility: hidden;" /> <br>
		<input type="text" name="GROUP_NM" class="setting_input"
			required="required" id="txt_group_nm" placeholder="Group Name"
			style="visibility: hidden; margin: 15px 0px 15px;">
		<div id="formsWrapper"></div>


	</form>
	<div id="uploadProgress"></div>
	<script>
		$('#myForm').ajaxForm({
			beforeSend : function() {
				console.log("start!")
			},
			uploadProgress : function(event, position, total, percentComplete) {
				document.getElementById("uploadProgress").innerHTML = percentComplete + "%";
	
			},
			complete : function(xhr) {
				document.getElementById("uploadProgress").innerHTML = "complete!";
			}
		});
		function uploadFile(target) {
			if (target.type == "file") {
				var formsWrapper = document.getElementById("formsWrapper");
				formsWrapper.innerHTML = "";
	
				var form_submit = document.getElementById("form_submit");
				form_submit.style.visibility = "visible";
	
				var txt_group_nm = document.getElementById("txt_group_nm");
				txt_group_nm.style.visibility = "visible";
				txt_group_nm.focus();
				
				var files = document.getElementById("files").files;
				for (var i = 0; i < files.length; i++) {
					makeForms(i, files[i].name);
				}
			}
		}
	
		function createElement(ele) {
			return document.createElement(ele);
		}
	
		function makeForms(idx, fileName) {
			var formsWrapper = document.getElementById("formsWrapper");
			var row = createElement("div");
			row.setAttribute("class", "row");
	
			var col3 = createElement("div");
			col3.setAttribute("class", "col-3");
	
			var videoThumbnail = createElement("div");
			videoThumbnail.setAttribute("class", "videoThumbnail");
			col3.appendChild(videoThumbnail);
	
			var col9 = createElement("div");
			col9.setAttribute("class", "col-9");
	
	
	
			var labelVIDEO_NM = createElement("label");
			labelVIDEO_NM.setAttribute("for", "VIDEO_NM");
			labelVIDEO_NM.innerHTML = "제목";
			var inputVIDEO_NM = createElement("input");
			inputVIDEO_NM.setAttribute("value", fileName);
			inputVIDEO_NM.setAttribute("type", "text");
			inputVIDEO_NM.setAttribute("name", "videoVO[" + idx + "].VIDEO_NM");
			inputVIDEO_NM.setAttribute("class", "setting_input");
			col9.appendChild(labelVIDEO_NM);
			col9.appendChild(inputVIDEO_NM);
	
			var labelVIDEO_DESC = createElement("label");
			labelVIDEO_DESC.innerHTML = "설명";
			var txtareaVIDEO_DESC = createElement("textarea");
			txtareaVIDEO_DESC.setAttribute("class", "setting_input");
			txtareaVIDEO_DESC.setAttribute("name", "videoVO[" + idx + "].VIDEO_DESC");
			col9.appendChild(labelVIDEO_DESC);
			col9.appendChild(txtareaVIDEO_DESC);
	
			var labelVIDEO_TAG = createElement("label");
			labelVIDEO_TAG.setAttribute("for", "VIDEO_TAG");
			labelVIDEO_TAG.innerHTML = "태그";
			var labelVIDEO_TAG_GB = createElement("span");
			labelVIDEO_TAG_GB.innerHTML = "(쉼표로 구분해주세요!)";
			labelVIDEO_TAG_GB.setAttribute("class", "tag_gb");
			labelVIDEO_TAG.appendChild(labelVIDEO_TAG_GB);
			var inputVIDEO_TAG = createElement("input");
			inputVIDEO_TAG.setAttribute("type", "text");
			inputVIDEO_TAG.setAttribute("name", "videoVO[" + idx + "].VIDEO_TAG");
			inputVIDEO_TAG.setAttribute("class", "setting_input");
			col9.appendChild(labelVIDEO_TAG);
			col9.appendChild(inputVIDEO_TAG);
	
			var labelSTEP = createElement("label");
			labelSTEP.setAttribute("for", "STEP");
			labelSTEP.innerHTML = "단계";
			var inputSTEP = createElement("input");
			inputSTEP.setAttribute("type", "text");
			inputSTEP.setAttribute("name", "videoVO[" + idx + "].STEP");
			inputSTEP.setAttribute("class", "setting_input");
			inputSTEP.setAttribute("required", "true");
			inputSTEP.onkeyup = function(e) {
				this.value = this.value.replace(/[^0-9]/g, "");
	
				e.stopPropagation();
			}
			inputSTEP.onblur = function(e) {
				this.value = this.value.replace(/[^0-9]/g, "");
	
				e.stopPropagation();
			}
			col9.appendChild(labelSTEP);
			col9.appendChild(inputSTEP);
	
			var labelNODE = createElement("label");
			labelNODE.setAttribute("for", "VIDEO_NODE_NO");
			labelNODE.innerHTML = "NODE";
			var inputNODE = createElement("input");
			inputNODE.setAttribute("type", "text");
			inputNODE.setAttribute("name", "videoVO[" + idx + "].VIDEO_NODE_NO");
			inputNODE.setAttribute("class", "setting_input");
			inputNODE.setAttribute("required", "true");
			inputNODE.onkeyup = function(e) {
				this.value = this.value.replace(/[^0-9]/g, "");
	
				e.stopPropagation();
			}
			inputNODE.onblur = function(e) {
				this.value = this.value.replace(/[^0-9]/g, "");
	
				e.stopPropagation();
			}
			col9.appendChild(labelNODE);
			col9.appendChild(inputNODE);
	
			var labelP_NODE = createElement("label");
			labelP_NODE.setAttribute("for", "PARENT_VIDEO_NODE_NO");
			labelP_NODE.innerHTML = "PARENT NODE";
			var inputP_NODE = createElement("input");
			inputP_NODE.setAttribute("type", "text");
			inputP_NODE.setAttribute("name", "videoVO[" + idx + "].PARENT_VIDEO_NODE_NO");
			inputP_NODE.setAttribute("class", "setting_input");
			inputP_NODE.setAttribute("required", "true");
			inputP_NODE.onkeyup = function(e) {
				this.value = this.value.replace(/[^0-9]/g, "");
	
				e.stopPropagation();
			}
			inputP_NODE.onblur = function(e) {
				this.value = this.value.replace(/[^0-9]/g, "");
	
				e.stopPropagation();
			}
			col9.appendChild(labelP_NODE);
			col9.appendChild(inputP_NODE);
	
			row.appendChild(col3);
			row.appendChild(col9);
			formsWrapper.appendChild(row);
		}
	</script>



</body>
</html>

