$(function () {
    updateGroupList();  //update table of group
    $('#saveGroupFromModal').on('click', event => saveGroup(event));    //save group's object in data base
    $('#editGroupFromModal').on('click', event => editGroup(event));    //update group's object in data base
    $('#deleteGroupFromModal').on('click', event => deleteGroup(event));    //delete group's object in data base
})

// displaying group's nested commands in a modal window
function showTeamInGroupList(id) {
    $('#edit-modal').modal('hide');
    $('#delete-modal').modal('hide');

    $('#teamid').empty();
    $.ajax({
        type: 'GET',
        url: '/api/group/team/' + id,
        dataType: 'json',
        contentType: 'application/json',
        success: function (teamListInGroup) {

            $('#teamid').append(
                "<tr>" +
                "<th scope=\"col\">Team Name</th>" +
                "<th scope=\"col\">Wins</th>" +
                "<th scope=\"col\">Number of draws</th>" +
                "<th scope=\"col\">Defeats</th>" +
                "<th scope=\"col\">Sum point</th>" +
                "<th scope=\"col\">Edit</th>" +
                "<th scope=\"col\">Delete</th>" +
                "</tr>"
            )

            teamListInGroup.forEach(team =>
                $('#teamid').append(
                    "<tr>" +
                    "<td scope=\"col\">" + team.name + "</td>" +
                    "<td scope=\"col\">" + getNumOfWin(team.id) + "</td>" +
                    "<td scope=\"col\">" + getNumOfDraw(team.id) + "</td>" +
                    "<td scope=\"col\">" + getNumOfLose(team.id) + "</td>" +
                    "<td scope=\"col\">" + ((getNumOfWin(team.id) * 3) + getNumOfDraw(team.id)) + "</td>" +
                    "<td scope=\"col\"><button type='button' onclick='editTeam()'>Edit</button></td>" +
                    "<td scope=\"col\"><button type='button' onclick='deleteTeam()'>Delete</button></td>" +
                    "</tr>"
                )
            )
        }
    })
}

//passing an object to the modal window for editing
function editGroupForm(id) {
    $.ajax({
        url: '/api/group/' + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (group) {
            $('#editGroupId').val(group.id);
            $('#editGroupName').val(group.name);

            $('#editGroupTeams').empty();
            group.teams.forEach(team =>
                $('#editGroupTeams').append(
                    "<option style='display: none'>" + team.id + "</option>" +
                    "<option>" + team.name + "</option>"
                )
            )

            $('#edit-footer').empty();
            $('#edit-footer').append(
                "<button type=\"button\" class=\"btn btn-outline-primary\" data-toggle=\"modal\"" +
                "data-target=\"#exampleModal\" onclick='showTeamInGroupList(" + group.id + ")'>Edit list of teams</button>"
            )

            $('#edit-modal').modal('show');
        }
    })
}

//passing an object to the modal window for deleting
function deleteGroupForm(id) {
    $.ajax({
        url: '/api/group/' + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (group) {
            $('#deleteGroupId').val(group.id);
            $('#deleteGroupName').val(group.name);
            $('#delete-modal').modal('show');
        }
    })
}

function saveGroup(e) { //save group's object in data base
    e.preventDefault()
}

function editGroup(e) { //update group's object in data base
    e.preventDefault()

    let group = {
        id: $('#editGroupId').val(),
        name: $('#editGroupName').val()
    };


    $.ajax({
        url: '/api/group/update',
        method: 'PUT',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(group),
        complete: function () {
            $('#edit-modal').modal('hide');
            updateGroupList();
        }
    })
}

function deleteGroup(e) {   //delete group's object in data base
    e.preventDefault()
}

function updateGroupList() {    //update table of group
    $('#groupid').empty();
    $.ajax({
        type: 'GET',
        url: '/api/group/list',
        success: function (groupList) {
            groupList.forEach(group =>
                $('#groupid').append(
                    "<tr>" +
                    "<td scope=\"col\">" + group.id + "</td>" +
                    "<td scope=\"col\">" + group.name + "</td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-light\" data-toggle=\"modal\" data-target=\"#exampleModal\" onclick='showTeamInGroupList(" + group.id + ")'>+</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-light\" onclick='editGroupForm(" + group.id + ")'>edit</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-danger\" onclick='deleteGroupForm(" + group.id + ")'>delete</button></td>" +
                    "</tr>"
                ))
        }
    })
}


function getNumOfWin(id) { //getting count of team's win
    let result = 0;
    $.ajax({
        type: 'GET',
        url: '/api/game/win/team/' + id,
        async: false,
        success: function (numberOfWin) {
            result = numberOfWin;
        }
    })
    return result;
}

function getNumOfLose(id) {   //getting count of team's lose
    let result = 0;
    $.ajax({
        type: 'GET',
        url: '/api/game/lose/team/' + id,
        async: false,
        success: function (numberOfLose) {
            result = numberOfLose;
        }
    })
    return result;
}

function getNumOfDraw(id) { //getting count of team's draw
    let result = 0;
    $.ajax({
        type: 'GET',
        url: '/api/game/draw/team/' + id,
        async: false,
        success: function (numberOfDraw) {
            result = numberOfDraw;
        }
    })
    return result;
}

