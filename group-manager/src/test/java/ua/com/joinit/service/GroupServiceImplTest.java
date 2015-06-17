package ua.com.joinit.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.joinit.GroupManagerBaseAppTest;
import ua.com.joinit.dao.GroupDAO;
import ua.com.joinit.entity.Group;
import ua.com.joinit.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by krupet on 17.06.2015.
 */
public class GroupServiceImplTest extends GroupManagerBaseAppTest {

    @Autowired
    @Mock
    private GroupDAO groupMockDAO;

    @Autowired
    @InjectMocks
    private GroupService groupService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostGroup() throws Exception {

        when(groupMockDAO.postGroup(any(Group.class))).thenReturn(new Group());
        Group dbGroup = groupService.postGroup(new Group());

        verify(groupMockDAO, times(1)).postGroup(any(Group.class));
        assertNotNull(dbGroup);
    }

    @Test
    public void testGetGroupById() throws Exception {

        when(groupMockDAO.getGroupById(anyLong())).thenReturn(new Group());
        Group dbGroup = groupService.getGroupById(1L);

        verify(groupMockDAO, times(1)).getGroupById(anyLong());
        assertNotNull(dbGroup);
    }

    @Test
    public void testGetAllGroups() throws Exception {

        when(groupMockDAO.getAllGroups()).thenReturn(new ArrayList<>());
        List<Group> groupList = groupService.getAllGroups();

        verify(groupMockDAO, times(1)).getAllGroups();
        assertNotNull(groupList);
    }

    @Test
    public void testUpdateGroup() throws Exception {

        when(groupMockDAO.updateGroup(any(Group.class))).thenReturn(new Group());
        Group dbGroup = groupService.updateGroup(new Group());

        verify(groupMockDAO, times(1)).updateGroup(any(Group.class));
        assertNotNull(dbGroup);
    }

    @Test
    public void testDeleteGroup() throws Exception {

        when(groupMockDAO.deleteGroup(any(Group.class))).thenReturn(null);
        Group dbGroup = groupService.deleteGroup(new Group());

        verify(groupMockDAO, times(1)).deleteGroup(any(Group.class));
        assertNull(dbGroup);
    }

    @Test
    public void testAddEvent() throws Exception {

        when(groupMockDAO.addEvent(anyLong(), anyLong())).thenReturn(new Group());
        Group dbGroup = groupService.addEvent(1L, 1L);

        verify(groupMockDAO, times(1)).addEvent(anyLong(), anyLong());
        assertNotNull(dbGroup);
    }

    @Test
    public void testRemoveEvent() throws Exception {

        when(groupMockDAO.removeEvent(anyLong(), anyLong())).thenReturn(new Group());
        Group dbGroup = groupService.removeEvent(1L, 1L);

        verify(groupMockDAO, times(1)).removeEvent(anyLong(), anyLong());
        assertNotNull(dbGroup);
    }

    @Test
    public void testGetAllUsersInAGroupByGroupID() throws Exception {

        when(groupMockDAO.getAllUsersInAGroupByGroupID(anyLong())).thenReturn(new ArrayList<>());
        List<User> userList = groupService.getAllUsersInAGroupByGroupID(1L);

        verify(groupMockDAO, times(1)).getAllUsersInAGroupByGroupID(anyLong());
        assertNotNull(userList);
    }
}