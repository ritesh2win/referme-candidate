async function uploadFile() {
  let formData = new FormData();
  formData.append("file", fileupload.files[0]);
  let response = await fetch('/upload', {
    method: "POST",
    body: formData
  });

  if (response.status == 200) {
    alert("File successfully uploaded.");
  }
}
