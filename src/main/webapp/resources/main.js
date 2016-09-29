$('#upload').on('click', function() {
    var formData = new FormData();
    console.log($('#file')[0].files[0]);
    var file=$('#file')[0].files[0];
    console.log(file);
   

    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url: '/uploadFile',
        type: 'POST',
        data: formData,
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            console.log("Success");
}
    });
})
