package tiny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
@RequestMapping(path = "/user") // This means URL's start with /demo (after Application path)
@CrossOrigin(origins = "http://localhost:4200",
        allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS, RequestMethod.DELETE},
        allowedHeaders = {"x-requested-with", "accept", "authorization", "content-type"},
        exposedHeaders = {"access-control-allow-headers", "access-control-allow-methods", "access-control-allow-origin", "access-control-max-age", "X-Frame-Options"})
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;


    @GetMapping(path = "/{id}")
    public @ResponseBody
    User getOneUser(@PathVariable int id) {
        return userRepository.findOne(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    User addNewUser(@RequestParam String name, @RequestParam String type) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User user = new User();
        user.setName(name);
        user.setType(type);


        userRepository.save(user);
        return user;
    }

    @PostMapping(path = "/edit")
    public @ResponseBody
    User editUser(@RequestBody User request) {

        int userId;

        userId = request.getUserid();

        if (userId == 0) {
            return addNewUser(request.getName(), request.getType());
        }
        User foundUser = userRepository.findOne(userId);

        if (!request.getName().equals("")) {
            foundUser.setName(request.getName());
        }

        if (!request.getType().equals("")) {
            foundUser.setType(request.getType());
        }

        userRepository.save(foundUser);

        return foundUser;
    }


    @PostMapping(path = "/delete")
    public @ResponseBody
    int deleteUser(@RequestBody String id) {
        int userId = Integer.parseInt(id);

        userRepository.delete(userId);
        return userId;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
