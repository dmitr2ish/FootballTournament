$(function () {
    updateGroupList();
    $('#saveGroupFromModal').on('click', event => saveGroup(event));
    $('#editGroupFromModal').on('click', event => editGroup(event));
    $('#deleteGroupFromModal').on('click', event => deleteGroup(event));
})

function saveGroup(e) {
    e.preventDefault()
}

function editGroup(e) {
    e.preventDefault()

    let group = {
        id: $('#editGroupId').val(),
        name: $('#editGroupName').val()
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

function deleteGroup(e) {
    e.preventDefault()
}

function updateGroupList() {
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

function editGroupFrom(id) {
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
            $('#edit-modal').modal('show');
        }
    })
}

function deleteGroupForm(id) {
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

function showTeamInGroupList(id) {
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

function getNumOfWin(name) {
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

function getNumOfLose(name) {
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

function getNumOfDraw(name) {
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

