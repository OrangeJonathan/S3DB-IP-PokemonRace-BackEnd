// package dev.pokemonracer.IntegrationTests.Controller;

// import dev.pokemonracer.model.User;
// import dev.pokemonracer.serviceInterfaces.IFriendService;
// import dev.pokemonracer.controller.FriendController;
// import dev.pokemonracer.dto.UserDTO;
// import dev.pokemonracer.mapper.UserMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;

// import java.util.Arrays;
// import java.util.List;

// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// public class FriendControllerIT {

//     @Mock
//     private IFriendService friendService;

//     @Mock
//     private UserMapper userMapper;

//     @InjectMocks
//     private FriendController friendController;

//     private MockMvc mockMvc;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//         mockMvc = MockMvcBuilders.standaloneSetup(friendController).build();
//     }

//     @Test
//     public void testGetFriends() throws Exception {
//         User user = new User();
//         UserDTO userDTO = new UserDTO();
//         when(friendService.GetAcceptedFriendsByAuth0Id(anyString())).thenReturn(Arrays.asList(user));
//         when(userMapper.toUserDTO(user)).thenReturn(userDTO);

//         mockMvc.perform(get("/api/friends")
//                 .param("auth0Id", "auth0id123")
//                 .param("accepted", "true")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testSendFriendRequest() throws Exception {
//         mockMvc.perform(post("/api/friends")
//                 .param("senderAuth0Id", "auth0id123")
//                 .param("receiverEmail", "test@example.com")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testAcceptFriendRequest() throws Exception {
//         mockMvc.perform(put("/api/friends")
//                 .param("senderAuth0Id", "auth0id123")
//                 .param("receiverAuth0Id", "auth0id456")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     public void testRemoveFriend() throws Exception {
//         mockMvc.perform(delete("/api/friends")
//                 .param("senderAuth0Id", "auth0id123")
//                 .param("receiverAuth0Id", "auth0id456")
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }
// }