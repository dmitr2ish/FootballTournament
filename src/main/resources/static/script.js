$(function () {
    updateGroupList();  //update table of group
    $('#saveGroupFromModal').on('click', event => saveGroup(event));    //save group's object in data base
    $('#editGroupFromModal').on('click', event => editGroup(event));    //update group's object in data base
    $('#deleteGroupFromModal').on('click', event => deleteGroup(event));    //delete group's object in data base
    $('#editTeamFromModal').on('click', event => editTeam(event));
    $('#deleteTeamFromModal').on('click', event => deleteTeam(event));
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
                    "<td scope=\"col\"><button type='button' class=\"btn btn-dark\" onclick='editTeamForm(" + team.id + ")'>Edit</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-dark\" onclick='deleteTeamForm(" + team.id + ")'>Delete</button></td>" +
                    "</tr>"
                )
            )
        }
    })
}

//passing an object Team to the modal window for editing
function editTeamForm(id) {
    $.ajax({
        url: '/api/team/' + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (team) {
            $('#editTeamId').val(team.id);
            $('#editTeamName').val(team.name);

            $('#edit-team-modal').modal('show');
        }
    })
}

//passing an object Team to the modal window for editing
function deleteTeamForm(id) {
    $.ajax({
        url: '/api/team/' + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (team) {
            console.log(team.id);
            $('#deleteTeamId').val(team.id);
            $('#deleteTeamName').val(team.name);
            $('#deleteGroupIdInTeamModal').val(team.idOfGroup);
            $('#delete-team-modal').modal('show');
        }
    })
}

//passing an object Group to the modal window for editing
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
                "<button type=\"button\" class=\"btn btn-outline-dark\" data-toggle=\"modal\"" +
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

function editTeam(e) {
    e.preventDefault();

    let team = {
        id: $('#editTeamId').val(),
        name: $('#editTeamName').val()
    }

    $.ajax({
        url: '/api/team/update',
        method: 'PUT',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(team),
        complete: function () {
            $('#edit-team-modal').modal('hide');
            updateTeamListFromEditTeam(team.id);
        }
    })
}

function deleteTeam(e) {
    e.preventDefault()

    let team = {
        id: $('#deleteTeamId').val(),
        name: $('#deleteTeamName').val(),
        idOfGroup: $('#deleteGroupIdInTeamModal').val()
    };

    console.log(team.idOfGroup);

    $.ajax({
        url: '/api/team/delete',
        method: 'DELETE',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(team),
        complete: function () {
            $('#delete-team-modal').modal('hide');
            updateTeamList(team.idOfGroup);
        }
    })
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

//delete group's object in data base
function deleteGroup(e) {
    e.preventDefault()

    let group = {
        id: $('#deleteGroupId').val(),
        name: $('#deleteGroupName').val()
    };


    $.ajax({
        url: '/api/group/delete',
        method: 'DELETE',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(group),
        complete: function () {
            $('#delete-modal').modal('hide');
            updateGroupList();
        }
    })
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
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-dark\" data-toggle=\"modal\" data-target=\"#exampleModal\" onclick='showTeamInGroupList(" + group.id + ")'>+</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-dark\" onclick='editGroupForm(" + group.id + ")'>edit</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-danger\" onclick='deleteGroupForm(" + group.id + ")'>delete</button></td>" +
                    "</tr>"
                ))
        }
    })
}

function updateTeamListFromEditTeam(teamId) {
    $('#teamid').empty();
    $.ajax({
        type: 'GET',
        url: '/api/team/teamsIdTeam/' + teamId,
        dataType: 'json',
        contentType: 'application/json',
        success: function (teamList) {
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

            teamList.forEach(team =>
                $('#teamid').append(
                    "<tr>" +
                    "<td scope=\"col\">" + team.name + "</td>" +
                    "<td scope=\"col\">" + getNumOfWin(team.id) + "</td>" +
                    "<td scope=\"col\">" + getNumOfDraw(team.id) + "</td>" +
                    "<td scope=\"col\">" + getNumOfLose(team.id) + "</td>" +
                    "<td scope=\"col\">" + ((getNumOfWin(team.id) * 3) + getNumOfDraw(team.id)) + "</td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-dark\" onclick='editTeamForm(" + team.id + ")'>Edit</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-dark\" onclick='deleteTeamForm(" + team.id + ")'>Delete</button></td>" +
                    "</tr>"
                )
            )
        }
    })
}

function updateTeamList(GoupId) {
    $('#teamid').empty();
    $.ajax({
        type: 'GET',
        url: '/api/team/teamsIdGroup/' + GoupId,
        dataType: 'json',
        contentType: 'application/json',
        success: function (teamList) {
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

            teamList.forEach(team =>
                $('#teamid').append(
                    "<tr>" +
                    "<td scope=\"col\">" + team.name + "</td>" +
                    "<td scope=\"col\">" + getNumOfWin(team.id) + "</td>" +
                    "<td scope=\"col\">" + getNumOfDraw(team.id) + "</td>" +
                    "<td scope=\"col\">" + getNumOfLose(team.id) + "</td>" +
                    "<td scope=\"col\">" + ((getNumOfWin(team.id) * 3) + getNumOfDraw(team.id)) + "</td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-dark\" onclick='editTeamForm(" + team.id + ")'>Edit</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-dark\" onclick='deleteTeamForm(" + team.id + ")'>Delete</button></td>" +
                    "</tr>"
                )
            )
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

