package com.example.footballTournament.controller.rest;

import com.example.footballTournament.entity.Group;
import com.example.footballTournament.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupRestController {
    final private GroupService groupService;

    @Autowired
    public GroupRestController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Group> addGroup(@RequestBody Group group) {
        return (groupService.saveGroup(group) != null)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/list")
    public List<Group> getAllUsers() {
        return  groupService.getAllGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getUserById(@PathVariable(name = "id") long id) {
        final Group group = groupService.getByGroupId(id);
        return group != null
                ? new ResponseEntity<>(group, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody Group group) {
        groupService.updateGroup(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
