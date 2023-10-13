package by.pizzzadog.controllers;

import by.pizzzadog.constant.Routes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {Routes.API_ROUTE})
@CrossOrigin
public class BaseController {
}
