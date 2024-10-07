# Software Architecture
- The design at the highest level, think of big picture as the entire system
- Higher producitivity beause there is a clear design and architecture
- Higher quality

## Architecture Overview and process

**Kruchten's 4 + 1 Model**
- Logical View focus on achieving the functional requirements
- Process View focus on achieving non-functional requirements such as performance and availablity as well as execution order
- Development View: hierarchical view, component structure, what is involved in the development. Such as languages, tool used, and project management
- Physical View: how about component mapped to hardware
- Scenario: How object and process interact

### Component Diagram
**Component**: independent encapsulated units within a system
**Component Diagram** use to visualize how system pieces to interact. How level structure
- Ball Connector represent provided interface
- Socket Connector represent required interface
All third party related library also need to be intergrated into component diagram


### Package Diagram
**Package**: a way to group related elements of software together. Contains data, classes and even other packages
- Package elements can denote with private or public by using plus or miners
- Package can expose interfaces
- Use dashed line with arrow to show package importing from other package
- Also used dashed line with arrow to indicate package merging
- Add label to indicate with arrow, `<<merge>>`, `<<import>>` or `<<access>>`
- Get to know the namespace of your system and who knows who

### Deployment Diagram
**Specification Diagram**
Overview of artifacts(physical result) and deployment targets
**Instance Level diagram**
Specific, map artifact to a specific deployment target
**Node**
Deployment target contains artifact for execution. Look like a 3D box

- Look at artifaces, libraries, main component, machines and devices

### Activity Diagram
**Activity**
Actions performed by the system
- this diagrams has start and end indicator (circle and two circle). arrow indicating workflow and rectangle as conditions
- Map out high level behaviors, do not care out implementations or code

-----------------------
## Software Architetural System
### Language Based system
**Abstract Data Types and OOP**

Organized data into a meaning way. Related attributes and behaviors are grouped together via encapsulation

**Main program and Subroutine**
- This is a archetitural design usually applies to procedural or functional programming language where your divide main program into subroutine with procedural call
- Abstract Data type is supported in procedural programming but inheritance is not explicitly supported
- Main concern for procedural programming is behaviors of function and how data flow through different procedures
- Suitable for computational system

### Repository based system
**Data Centric Software Architecture**
- Central Data serves data to all the components and Data Accessors connect and query data from central data
- Central data is passive and not involving actively in business logic and computation
- Data accessors do not interact with each other, instead they talk to Central data

### Layered System
**layer**

A set of components works together for a common problem
- The components in a layer only interact within same layers or adjacent layer
- Bottom layers provide abstraction for layer above it. Eg: Interface provide abstraction for any higher layer implementation
- Layering system provide separation of concerns, commonly : **Presentation, Logic, Data**
- Layer system is a good example of pricinple of least knowledge
- Practically, layer system may allow information to pass through

**Client Server n-Tier**
- A Client-Server Request-Response system is a typical n-Tier system
- Can be synchorous or asynchorous
- Components and be on different physical machines

### Interpreter-based System
- interpreter built in to software system and is responsible for executing scrips, marcos and rules **written by users**
- Eg: you type in a math formula in excel, and execute by the excel interpreter to calculate result
- Interpreter can be slow. It make developer fast to write code but slower in computer to run

### Data Flow (Pipe and Filter) system
- A series of trasformation on sets of data
- Filter: perform transformation on data input
- Pipe: service as connectors
- Datasource ---(pipe)---> Filter --(pipe)---> Filter ---(pipe)---> Target
- The order may infect the end result
- Loosely coupled filter run independently on input data
- **Disadvantage**:
  - Blocking on large data. One filter is overload while other downstream filter may idle
  - Bad for interactive application, data transformation require time especially on large data

### Implicit Invocation System
#### Event Based Architecture
- Events as fundemental such as Signals, User inputs, Messages and Data
- Events act as indicators of change and Triggers to functions

**Implicit Invocation**
- Functions are not in direct communcation with each others
- Mediated by Event Bus. 
- Event consumers register with Event Bus, and Event bus distribute events to subscriber
- in Event based architecture, a *Race Condition* is worth considering

**Semaphore**
- a synchronizaion primitive to control access to shared resource
- state of availables or unavailable
- process check the state of semaphore before accessing the data. And make the semaphore unavailable when on the data

In Event based architecture, events and functions do not occur in a predictable way. No guarantee when, whether events happens and how long it last

#### Publish-Subscribe Architecture
- It is different from Event based. Publisher can only send message, subscriber can only receive message
- In a simple system, Subsriber register directly with Publisher through callback. And publisher communicate via a procedural call to callbacks
- In complicated system, a event-bus may be involved but still, publisher can only send message subscriber can only receive

### Process Control System
**4 Key component of process control**
Monitor ---> Analyze ---> Plan ---> Execute
#### Feedback Loop
- Controller: Define the logic
- Sensor: monitoring the important metrics
- Actuator: method of manipulating the process
- Process: things being control

**Example**: In a smart home, the smoke detector (sensor) detect the smoke. The controller define the threshold of CO and when there is discrepency, it trigger the Alarm to go off (Actuator) which deliver sounds to the entire room. When sensor detect the CO level goes back to normal, it then tell the controller to send signal to alarm to stop. Here the CO level is the feedback

- Controller logic run continuosly but the loop runs only when feedback falls into a threshold

#### Type of process control loop
**Close Loop**
Information from the process (feedback) is used to control the same process

**Open Loop/Feed through**
Infomation output to the system and cannot check the sucess of the workflow. Such as a dryer with timer.
Information from upstream can use the control the down stream process


## Architecture in Practice

1. Software Architecture should consider both functional and non-functional requirements
2. Different groups of stateholder will have different non-functional requirments

### Quality Attributes
**definition**: Measurable properties used to guage design, runtime performance and usability

**Common Quality attributes from Developer perspective**

| Quality Attributes | Measures |
|----------|----------|
| Maintainability| ease of change  |
| Reusability   |   parts that can be used in another  |
| Flexibility   | adapt to requirement change  |
| Modifiability   | incorporate new or remove existing functions  |
| Testability  |  demonstrate errors via executable tests |
| Conceptual Integrity| consistency across the system |


**Common Quality attributes from user perspective**
| Quality Attributes |
|---|
|Availability|
|Interoperability (ability to understand and sahre data with external)|
|Security|
|Performance|
|Usability|


### Analyzing / Evaluating an architecture with Quality Senario
1. Stimulus
*Describe the event or condition that triggers the scenario.*
- Example: A user submits a login request.

 1. Environment
*Define the context in which the scenario takes place.*
- Example: The production environment during peak traffic hours.

1. Artifact
*Specify the system component or service being evaluated.*
- Example: The authentication service.

1. Response
*Describe the expected behavior or outcome when the stimulus occurs.*
- Example: The system processes the login request within 2 seconds.

1. Response Measure
*Define criteria to evaluate the effectiveness of the response.*
- Example: 
  - Response time should be less than 2 seconds.
  - 95% of login attempts should be successful.


**Architecture tradoff analysis methods**
- Evaluator do not need to be familiar with architecture
- involves three teams:
  - Evaluator: designers, peers and outsiders
  - Project Decision Maker: PM, Tech lead, etc
  - Architecture stakeholder: End users, developers, etc

### Product Architecture
1. Loosely organizational structure usually leads to loosely coupled system
2. Extra works may be needed to build scalable software

### Product Line and Family
- A group of products or services that share the same platforms but with individual features.
- Eg: iphone and ipad are same product line based on iOS platform
- Up-front development investment would increase but later-on cost can be reduced

**Domain Engineering**

Development of the commonalities and variations within the product line

**Application Engineering**

Actually develoing the product on top of domain engineering and develop product-specific features

**Technicque to realize variations in product lines**
1. Adaption: component has one implementation but support multiple interface to change or add-on
2. Replacement: different component being supplied to the system
3. Extension: one common interface that adapted to all variations

### Architecture Tradeoff Analysis Method (ATAM)

Evaluate software architectures based on quality attributes

1. **Preparation**: Identify stakeholders and quality attributes
2. **Present Architecture**
3. **Identify Approach**: Discuss strategies and patterns
4. **Generate Scenarios**: Stake holder create scenarios representing quality attributes
5. **Analyze Secnarios**: evaluate how well the architecutre meets scenario requirements
6. **Find Risk and trade-off**
7. **Summarize findings**
8. **Follow up**