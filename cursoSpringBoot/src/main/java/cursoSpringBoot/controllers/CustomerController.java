package cursoSpringBoot.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yaml.snakeyaml.events.Event.ID;

import cursoSpringBoot.domain.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/customers")
public class CustomerController {

    //lista de datos de clientes
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
        new Customer(123, "Jim Cortés", "jcortes", "123456789"),
        new Customer(456, "Ana García", "agarcia", "987654321"),
        new Customer(789, "Carlos López", "clopez", "456789123"),
        new Customer(101, "María Rodríguez", "mrodriguez", "321654987")
    ));

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getCustomer(@PathVariable String username){
        for (Customer customer : customers) {
            if (customer.getUsername().equalsIgnoreCase(username)) {
                return ResponseEntity.ok(customer);               
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe cliente con username: " + username);
    }
    

    @PostMapping
    public ResponseEntity<?> postCustomer(@RequestBody Customer customer){
        customers.add(customer);
        // return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente "+ customer.getUsername());

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest() //toma la url actual de la petición
            .path("/{username}") //agrega el path variable a la url
            .buildAndExpand(customer.getUsername()) //expande el path variable con el username del cliente
            .toUri(); //convierte la URI a un objeto URI
        // return ResponseEntity.created(location).build(); //devuelve una respuesta 201 Created con la URI del nuevo cliente
        return ResponseEntity.created(location).body(customer); //devuelve una respuesta 201 Created con la URI del nuevo cliente y el cliente creado en el cuerpo de la respuesta
    }

    @PutMapping
    public ResponseEntity<?> putCustomer(@RequestBody Customer customer){
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                c.setName(customer.getName());
                c.setPassword(customer.getPassword());
                c.setUsername(customer.getUsername());

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        for (Customer c : customers) {
            if (c.getID() == id) {
                customers.remove(c);
                // return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado satisfactoriamente: " + c.getID());
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<?> patchCustomer(@RequestBody Customer customer){
        for (Customer c : customers) {
            if (c.getID() == customer.getID()) {
                if(customer.getName() != null){
                    c.setName(customer.getName());
                }
                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }
                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                }
                return ResponseEntity.ok("Cliente modificado satisfactoriamente: " + customer.getID());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fallo al modificar el cliente con ID: " + customer.getID());
    }

}
