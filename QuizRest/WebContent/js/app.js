$(document).ready(function() {
    console.log('app.js loaded');
    getData();
});

function getData() {
    $.ajax({
        type: 'GET',
        url: 'rest/quizzes/',
        dataType: 'json'
    }).done(function(data) {
        // console.log(data);
        buildTable(data);
    }).fail(function(err) {
        console.error('ajax fail');
        console.error(err);
    });
}

function buildTable(data) {
    // console.log('in buildTable()');
    var table = $('<table> <thead> <tr><th>Quiz List</th></tr> </thead>');
    var tbody = $('<tbody>');
    table.append(tbody);

    data.forEach(function(val, idx) {
        // console.log(val);
        var tr = $('<tr>');
        var td = $('<td>');
        var viewtd = $('<td>');
        viewtd.text('View');

        if (idx % 2 == 0) {
            tr.addClass('stripe');
        }

        td.text(val.name);
        td.attr("id", val.id);

        viewtd.click(function() {
            getQuiz(val.id);
        });

        tr.append(td, viewtd);
        tbody.append(tr);

    });
    $('#tableDiv').append(table);
}

function getQuiz(quizId) {
    // console.log(quizId);
    $.ajax({
        type: 'GET',
        url: 'rest/quizzes/' + quizId + '/questions',
        dataType: 'json'
    }).done(function(quiz) {
        // console.log(quiz);
        $('#tableDiv').empty();
        viewDetailedQuiz(quiz);
    }).fail(function(err) {
        console.error('ajax fail');
        console.error(err);
    });
}

function viewDetailedQuiz(quiz) {
    var h1 = $('<h1>');
    h1.text(quiz.name);
    $('#tableDiv').append(h1);

    var table = $('<table> <thead> <tr><th>Quiz List</th></tr> </thead>');
    var tbody = $('<tbody>');
    table.append(tbody);

    quiz.forEach(function(q, idx) {
        // console.log(val);
        var tr = $('<tr>');
        var td = $('<td>');
        var a1 = $('<td>');
        var a2 = $('<td>');
        var a3 = $('<td>');
        var a4 = $('<td>');


        if (idx % 2 == 0) {
            tr.addClass('stripe');
        }

        // console.log(q.answers);
        a1.text(q.answers[0].answerText);
        a2.text(q.answers[1].answerText);
        a3.text(q.answers[2].answerText);
        a4.text(q.answers[3].answerText);

        td.text(q.questionText);
        td.attr("id", q.id);

        tr.append(td, a1, a2, a3, a4);
        tbody.append(tr);

    });
    $('#tableDiv').append(table);
}
