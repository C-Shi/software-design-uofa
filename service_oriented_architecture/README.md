# Serviced Oriented Architecture
- Service Requester
- Service Provider
- Achieving software goals by building and using service rather than build one that do everything


### Service Principles
1. Modular: Service mean to be mixed and match, decoupled and reusable
2. Composable: should be able to use in combinations
3. Platform and Languages independent: follow standard communication protocols
4. Self-describing: Should describe how to interact with it
5. Self-advertising

### Web System Architecture
**Layer on Web systems**
1. Presentation --- Application ---- Data layers
2. Presentation Tier: (Web Browser & Web Server)
3. Application Tier: (Web Application Server) - can also interact with other service providers
4. Data Tier: (Database)

### HTTP refreshment
1. HTTP is based on TCP protocol. 
2. HTTP client request format: request-line, headers, blank line, and (message body)

    ```
    POST /api/users HTTP/1.1
    Host: example.com
    Content-Type: application/json
    Content-Length: 56

    {
    "name": "John Doe",
    "email": "johndoe@example.com"
    }
    ```

3. Server Response format: Status-line, Headers, a blank line and (message body)

    ```
    HTTP/1.1 200 OK
    Content-Type: application/json
    Content-Length: 43

    {
    "message": "User created successfully",
    "userId": "12345"
    }
    ```

4. URL encoding: converting special and non-ASCII characters into a format that can trasmit over internet. This ensure consistency and correctness, allowing reliable communcations

### Remote Procedure Call (RPC)
1. Allow client to invoke procedures that was implemented on the servers. Client and Servers can be on different machine or different virtual machine

2. contains client, server and interface definition languages (IDL)

3. Make a phone call is a RPC which trigger the rining on the other party. 

**Middleware**

Architecture used to faciliate communications between services operating environmentally different

### Object Brokers

**Common Object Request Broker Architecture (CORBA)**

A standard to describe what should be included in an Object Broker. Specification for Independence of the programming languages

**Object Request Brokder**

 describes middleware facilitating communication between distributed objects

- There isn't a single, standalone component labeled "ORB" that every system uses. Instead, each system typically incorporates an ORB as part of its implementation. Different vendors provide their own ORBs that comply with the CORBA specification.

- Each component defines its interfaces using IDL, but there isn't a universal IDL file for the entire system. Instead, each system or service has its own IDL definitions that describe how it interacts with other components.

## SOAP-XML based Web Service

**Web Services**

Some functionalities that is exposed and accessible using web technology. Web service use Request-Response cycle.

#### Service Invocation (SOAP)
- A standard allow service requester to invoke service
- It use XML as its message format, and standardized way to encode information
- Two type of SOAP messaging:
  - Document style:  contains XML document
  - RPC style: : invoking method, still a XML document but look similar to a method call
- SOAP request via HTTP are most commonly used via POST request, regardless of what it what to achieve

#### Service Description (WSDL)
- XML-Based language to Describe an interface of a web service: eg, input, output etc
- Based on SOAP web service standard
- WSDL document is machine readable
- Generate the code that adapt to the interface is called binding
- Key components: Definitions, Types, Message, Port Type, Binding, Service

#### Service Publication and Discovery (UDDI)
- Specification taht defines how to describe, discovering and integrating web services. 
- SOAP-based, decline since REST came out
- Service provider publish to UDDI registry. Service requester search the registry based on WSDL description, and bind

### Service Composition (BPEL)
- Combining serveral services and provide one interface
- Composite service will select and talk to different services based on condition and reponse to service requester at the end
- BPEL hight level composition language for web services

## REST Services
A **client server architecture** with request-response cycle. Providing separation of concerns where server manipulate the data, client provide user interface.
REST is a **layered system**. Interaction must be **stateless**, information is not kept when the session end. Information is **cacheable**. There is a **uniform interface** - HTTP method

#### Design REST service
- use conventional naming of URI
- get method should not alter the state of resource
- use plurals nouns for URI
- show the connection of resources on URI, eg: `/students/3/courses/2`
- use HTTP header to specify input output format `Content-Type` and `Accept`
- versioning the API properly
- provide proper HTTP status code

#### Microservices

**Microservice**

A process that is responsible for producing a single set of tasks. 

- Each single microservice may not be layered structure. As not all the microservice are intended for users to use
- Each microservices can control or manage its own data

**Benefits**
- Microservice can use languages, library and frameworks that best fit the job.
- Allow developer to try out new technology without making company wide committment
- microservices can have multiple replicas which introduce reduent, while different microservice may scaled independently

**Drawbacks**
- some level of centralized management is required to manage microservices distributed systems
- transaction may spread out through multiple microservcies
- testing distributed system is more complex