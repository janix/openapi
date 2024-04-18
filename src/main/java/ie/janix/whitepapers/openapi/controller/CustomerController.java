package ie.janix.whitepapers.openapi.controller;

import ie.janix.whitepapers.openapi.exception.CustomerLockedException;
import ie.janix.whitepapers.openapi.exception.CustomerNotFoundException;
import ie.janix.whitepapers.openapi.model.Customer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Sample Customer controller class to demonstrate OpenAPI Integration
 * Dummy method implementations are provided for demo purposes
 * Includes example usage of @OpenAPIDefinition, @Operation, @ApiResponses & @Parameter annotations
 */
@RestController
@RequestMapping("/api/customer")
@OpenAPIDefinition(servers = {@Server(url = "http://localhost:8080", description = "local"), @Server(url = "http://localhost:8091", description = "DEV")}, info = @Info(version = "1.0", title = "Customer API", description = "This is the Customer API"))
public class CustomerController {

  /**
   * Dummy find method
   *
   * @param id id of customer for retrieval
   * @return Dummy customer record
   * @throws CustomerNotFoundException if parameter value <=0 is passed
   */
  @Operation(summary = "Retrieve a Customer record")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Customer found",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Customer.class))}),
    @ApiResponse(responseCode = "401", description = "Unauthenticated",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Customer.class))}),
    @ApiResponse(responseCode = "403", description = "Forbidden",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Customer.class))}),
    @ApiResponse(responseCode = "404", description = "Customer not found",
      content = @Content)})
  @GetMapping("/{id}")
  public Customer findById(@Parameter(required = true, description = "Customer id") @PathVariable long id) throws CustomerNotFoundException {

    if (id <= 0) {
      throw new CustomerNotFoundException(id);
    }
    return new Customer("John", "Johnson", "john@test.com");
  }

  /**
   * Dummy find all method
   *
   * @return List of dummy customer records
   */
  @Operation(summary = "Get all Customers")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Customers retrieved successfully",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = Customer.class))))
  })
  @GetMapping("/")
  public Collection<Customer> findCustomers() {
    return new ArrayList<>() {{
      add(new Customer("John", "Johnson", "john@test.com"));
      add(new Customer("James", "Jameson", "james@test.com"));
    }};
  }

  /**
   * Dummy update method
   * @param id of customer for update
   * @param customer Updated customer data
   * @return  updated customer record
   * @throws CustomerNotFoundException if id <= 0 is passed
   * @throws CustomerLockedException if id of 999 is passed
   */
  @Operation(summary = "Update a Customer record")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Customer updated successfully",
      content = {@Content(mediaType = "application/json",
        schema = @Schema(implementation = Customer.class))}),
    @ApiResponse(responseCode = "400", description = "Customer record could not be updated",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "Customer not found",
      content = @Content)})
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Customer updateCustomer(
    @Parameter(required = true, description = "Customer id") @PathVariable("id") final long id, @RequestBody final Customer customer) throws CustomerLockedException, CustomerNotFoundException {

    if (id <= 0) {
      throw new CustomerNotFoundException(id);
    }

    if (id == 999) {
      throw new CustomerLockedException(id);
    }

    return customer;
  }
}
