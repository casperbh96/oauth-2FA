package com.mycompany;

import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.jdbc.Jdbc;
import org.jooby.mail.CommonsEmail;
import org.jooby.mvc.GET;
import org.jooby.mvc.Path;

import javax.sql.DataSource;

import static org.jooby.Route.GET;
import static org.jooby.Route.PUT;

/**
 * @author jooby generator
 */
public class App extends Jooby {
    {
        use(new Jdbc("db"));

        assets("/", "authentication.html");

        post("auth", req -> {
            User user = req.params(User.class);
            DataSource db = require(DataSource.class);

            if (user.isAuthenticated(db)) {
                return "<html><meta name=viewport content=width=device-width, initial-scale=1>" +
                        "<link rel=stylesheet href=https://www.w3schools.com/w3css/4/w3mobile.css>" +
                        "<body><header class=w3-container w3-card>" +
                        "    <h1>E-mail sent!</h1>" +
                        "</header>" +
                        "<form action=/emailAuth method=post>Enter code from e-mail: <br>" +
                        "<input name=emailAuth type=number /> " +
                        "<input name=email type=hidden value=" + user.getUsername() + " /> <br>" +
                        "<button type=submit>Confirm code</button></form></body></html>";
            } else {
                return "login failed for: " + user.getUsername();
            }
        });

        post("emailAuth", req -> {
            DataSource db = require(DataSource.class);

            if (User.emailAuthenticated(db, req.param("email").value(), req.param("emailAuth").value())) {
                return "<html><meta name=viewport content=width=device-width, initial-scale=1>" +
                        "<link rel=stylesheet href=https://www.w3schools.com/w3css/4/w3mobile.css>" +
                        "<body> logged in with:" +
                        "<p id='username'>" + req.param("email").value().toString() + "</p>" +
                        "<p id='json'>" + new Token(req.param("email").value()).toString() + "</p>" +
                        "</body></html>";
            } else {
                return "Wrong code.";
            }

        });
    }

    public static void main(final String[] args) {
        run(App::new, args);
    }
}
