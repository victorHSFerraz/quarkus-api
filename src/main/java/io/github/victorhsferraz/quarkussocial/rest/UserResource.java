package io.github.victorhsferraz.quarkussocial.rest;

import io.github.victorhsferraz.quarkussocial.domain.model.User;
import io.github.victorhsferraz.quarkussocial.domain.repository.UserRepository;
import io.github.victorhsferraz.quarkussocial.rest.dto.CreateUserRequest;
import io.github.victorhsferraz.quarkussocial.rest.dto.ResponseError;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserRepository repository;
    private Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @POST
    @Transactional // Somente para persistir dados
    public Response createUser(CreateUserRequest userRequest) {

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if (!violations.isEmpty()) {
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user);

        return Response
                .status(Status.CREATED)
                .entity(user)
                .build();
    }

    @GET
    // Sem transactional pois é uma consulta
    public Response listAllUsers() {
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        User user = repository.findById(id);

        if (user == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        repository.delete(user);
        return Response.noContent().build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData) {
        User user = repository.findById(id);

        if (user == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        user.setName(userData.getName());
        user.setAge(userData.getAge());

        // qualquer alteracao a uma entidade no contexto transacional sera commitado no
        // final do metodo

        return Response.noContent().build();
    }

}
