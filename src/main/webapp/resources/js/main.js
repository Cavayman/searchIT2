$(document).ready(function () {
    alert("asd");
    $(".menu").hide();
    $("#show").hide();
    showAllFiles();
});

$('#show').on('click', showMenu);
$('#search').on('click', search);
var currentFileName;

$('#upload').on('click', function () {
    var formData = new FormData();
    var file = $('#file')[0].files[0];
    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url: '/uploadFile',
        type: 'POST',
        data: formData,
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            $("#show").show();
            $('#wholeText').empty();
            var metadata=data.metaData;
            var title = $('<h1>').append(metadata.fileName);
            currentFileName=metadata.fileName;
            addIcon(metadata.fileName,metadata.id);
            var text = $('<p>');
           $.each(data.text,function(index, element) {
               text.append(element+' ');
           })
            $('#wholeText').append(title).append(text);
            $('body').animate({
                scrollTop: $('body')[0].scrollHeight
            }, 200);
            $.toaster({ message : "File size:"+metadata.fileSize+"<br>"
            +"File name:" +metadata.fileName+"<br>"
            +"Creation date:"+metadata.fileCreationDate,title:"Metadata" });
        }
    });
})
var show = 0;

function showMenu() {
    if (show != 1) {
        $("#menu").show();

        show = 1;
    } else {
        $("#menu").hide();
        show = 0;
    }
}

function search() {
    var wordToSearch = $('#searchWord').val();
    var lengthOfFile = $('#length').val();
    var limitOfFile = $('#limit').val();
    var includeMetaData = $('#includeMetaData:checked').val();
    if(includeMetaData=='on') {
        includeMetaData = true;
    }else {
        includeMetaData = false;
    }
        $.ajax({
        url: '/searchWithParams?q='+wordToSearch
        +'&length='+lengthOfFile
        +'&limit='+limitOfFile
        +'&includeMetaData=' +includeMetaData+
        '&fileName='+currentFileName,
        type: 'POST',
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            $("#show").show();
            $('#searchRes').empty();
            var metadata=data.metaData;
            var title = $('<h1>').append("Response");
            var text = $('<p>').append(JSON.stringify(data.metaData)+'<br>');
            text.append("Text: ");
            $.each(data.text,function(index, element) {
                text.append(element+' ');
            })
            $('#searchRes').append(title).append(text);
            if(metadata!=null) {
                $.toaster({
                    message: "File size:" + metadata.fileSize + "<br>"
                    + "File name:" + metadata.fileName + "<br>"
                    + "Creation date:" + metadata.fileCreationDate, title: "Metadata"
                });
            }
        }
    });
}
function showAllFiles() {
    $.ajax({
        url: '/showAllFiles',
        type: 'GET',
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            for (var index in data) {
                addIcon(data[index].fileName, data[index].id);
            }
        }
    });
}
function addIcon(titleName, id) {

    var title = $('<div>');
    title.addClass('title');
    var label = $('<label>');
    label.text(titleName);
    label.attr('id', id);
    title.append(label);
    var img = $('<div>');
    img.addClass('img');

    var icon = $('<i>');
    icon.addClass('fa fa-file-text fa-5x');
    icon.css('aria-hidden', 'true');

    var plate = $('<div>');
    plate.addClass('plate');

    var wrapper = $('<div>');
    wrapper.addClass('col-xs-3');

    img.append(icon);
    plate.append(img);
    plate.append(title);
    wrapper.append(plate);
    wrapper.click(getFileByPlate);
    $('.file-list').append(wrapper);

}

$('.plate').on('click',getFileByPlate);

function getFileByPlate() {
   var id=$(this).find("label").attr("id");
   $.ajax({
        url: '/showAllFiles?id='+id,
        type: 'POST',
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            $("#show").show();
            $('#wholeText').empty();
            var metadata=data.metaData;
            var title = $('<h1>').append(metadata.fileName);
            currentFileName=metadata.fileName;
            var text = $('<p>');
            $.each(data.text,function(index, element) {
                text.append(element+' ');
            })
            $('#wholeText').append(title).append(text);
            $('body').animate({
                scrollTop: $('body')[0].scrollHeight
            }, 500);
            $.toaster({ message : "File size:"+metadata.fileSize+"<br>"
            +"File name:" +metadata.fileName+"<br>"
            +"Creation date:"+metadata.fileCreationDate,title:"Metadata" });
            }
    });
}
