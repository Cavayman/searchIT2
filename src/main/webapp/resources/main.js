$(document).ready(function () {
    alert("asd");
    $(".menu").hide();
    $("#show").hide();
    showAllFiles();
});

$('#show').on('click', showMenu);
$('#search').on('click', search);


$('#upload').on('click', function () {
    var formData = new FormData();
    console.log($('#file')[0].files[0]);
    var file = $('#file')[0].files[0];
    console.log(file);


    formData.append('file', $('#file')[0].files[0]);
    $.ajax({
        url: '/uploadFile',
        type: 'POST',
        data: formData,
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            $("#show").show();
            $('#content').empty();
            var title = $('<h1>').text(data.title);
            addIcon(data.title, data.id);
            var text = $('<p>').text(data.text);
            $('#content').append(title).append(text);
            $('body').animate({
                scrollTop: $('body')[0].scrollHeight
            }, 500);
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
    console.log(wordToSearch);
}
function showAllFiles() {
    $.ajax({
        url: '/showAllFiles',
        type: 'GET',
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
            for (var index in data) {
                addIcon(data[index].title, data[index].id);
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
    console.log($(this));
    console.log($(this).children("label"));
    console.log(id);
    $.ajax({
        url: '/showAllFiles?id='+id,
        type: 'POST',
        processData: false,  // tell jQuery not to process the data
        contentType: false,  // tell jQuery not to set contentType
        success: function (data) {
                $("#show").show();
                $('#content').empty();
                var title = $('<h1>').text(data.title);
                var text = $('<p>').text(data.text);
                $('#content').append(title).append(text);
                $('body').animate({
                    scrollTop: $('body')[0].scrollHeight
                }, 500);
            }
    });
}

