/**
 * Created by fiona on 2/25/15.
 */
$(function(){

    $("#form").submit(function(e){
        e.preventDefault();

        var county= $('#county').val();
        var type= $('#type').val();
        var area= $('input[name=area]').val();
        var room= $('input[name=rum]').val();
        var price= $('input[name=pris]').val();
        var fee= $('input[name=avgift]').val();



        $.ajax({
            type:"GET",
            url: 'query.php?lan=' + county+ '&objekttyp=' + type + '&area=' + area + '&rum=' + room + '&pris=' + price + '&avgift=' + fee + '&orderbyCOL=pris' + '&orderbyASC=1',
            success: function(data){
                var res= $.parseJSON(data);
                var table = $('#results');
                table.html('');
                $.each(res, function(index,value){
                    var results =  $("<tr>");
                    results.append($("<td>").html(res[index]['lan']));
                    results.append($("<td>").html(res[index]['objekttyp']));
                    results.append($("<td>").html(res[index]['area']));
                    results.append($("<td>").html(res[index]['rum']));
                    results.append($("<td>").html(res[index]['pris']));
                    results.append($("<td>").html(res[index]['avgift']));
                    table.append(results);
                });



            }

        })
    });


});