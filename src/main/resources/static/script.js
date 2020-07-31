$(function () {
    updateGroupList();  //update table of group
    $('#saveGroupFromModal').on('click', event => saveGroup(event));    //save group's object in data base
    $('#editGroupFromModal').on('click', event => editGroup(event));    //update group's object in data base
    $('#deleteGroupFromModal').on('click', event => deleteGroup(event));    //delete group's object in data base
})

function saveGroup(e) { //save group's object in data base
    e.preventDefault()
}

function editGroup(e) { //update group's object in data base
    e.preventDefault()

    let group = {
        id: $('#editGroupId').val(),
        name: $('#editGroupName').val(),
        // role: $('#ed')
    };

    console.log("editGroup: " + group.name)

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
            console.log(groupList);
            groupList.forEach(group =>
                $('#groupid').append(
                    "<tr>" +
                    "<td scope=\"col\">" + group.id + "</td>" +
                    "<td scope=\"col\">" + group.name + "</td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-light\" data-toggle=\"modal\" data-target=\"#exampleModal\" onclick='showTeamInGroupList(" + group.id + ")'>+</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-light\" onclick='editGroupFrom(" + group.id + ")'>edit</button></td>" +
                    "<td scope=\"col\"><button type='button' class=\"btn btn-outline-danger\" onclick='deleteGroupForm(" + group.id + ")'>delete</button></td>" +
                    "</tr>"
                ))
        }
    })
}

function editGroupFrom(id) {    //passing an object to the modal window for editing
    console.log(id);
    $.ajax({
        url: '/api/group/' + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (group) {
            console.log("group: " + group.name);
            $('#editGroupId').val(group.id);
            $('#editGroupName').val(group.name);
            group.teams.forEach(team =>
                $('#editGroupTeams').append(
                    "<option>" + team.name + "</option>"
                ))
            $('#edit-modal').modal('show');
        }
    })
}

function deleteGroupForm(id) {  //passing an object to the modal window for deleting
    console.log(id);
    $.ajax({
        url: '/api/group/' + id,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (group) {
            console.log("group: " + group.name);
            $('#deleteGroupId').val(group.id);
            $('#deleteGroupName').val(group.name);
            $('#delete-modal').modal('show');
        }
    })
}

function showTeamInGroupList(id) { //displaying group's nested commands in a modal window
    console.log('id: ' + id);
    $('#teamid').empty();
    $.ajax({
        type: 'GET',
        url: '/api/group/team/' + id,
        dataType: 'json',
        contentType: 'application/json',
        success: function (teamListInGroup) {
            console.log(teamListInGroup);
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
                    "<td scope=\"col\">" + getNumOfWin(team.name) + "</td>" +
                    "<td scope=\"col\">" + getNumOfDraw(team.name) + "</td>" +
                    "<td scope=\"col\">" + getNumOfLose(team.name) + "</td>" +
                    "<td scope=\"col\">" + ((getNumOfWin(team.name) * 3) + getNumOfDraw(team.name)) + "</td>" +
                    "<td scope=\"col\"><button type='button' onclick='editTeam()'>Edit</button></td>" +
                    "<td scope=\"col\"><button type='button' onclick='deleteTeam()'>Delete</button></td>" +
                    "</tr>"
                ))
        }
    })
}

function getNumOfWin(name) { //getting count of team's win
    let result = 0;
    $.ajax({
        type: 'GET',
        url: '/api/game/win/team/' + name,
        async: false,
        success: function (number1) {
            result = number1;
        }
    })
    return result;
}

function getNumOfLose(name) {   //getting count of team's lose
    let result = 0;
    $.ajax({
        type: 'GET',
        url: '/api/game/lose/team/' + name,
        async: false,
        success: function (number) {
            result = number;
        }
    })
    return result;
}

function getNumOfDraw(name) { //getting count of team's draw
    let result = 0;
    $.ajax({
        type: 'GET',
        url: '/api/game/draw/team/' + name,
        async: false,
        success: function (number) {
            result = number;
        }
    })
    return result;
}

