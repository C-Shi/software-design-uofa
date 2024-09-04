# Object Oriented Design

## Module 1: Welcome to Software Design and Architecture

### Intro

- What is the role of Architect and Design Role?
  - Outlining a software solution to target a specific problem
  - Desigin details of individual components and responsibility
  - Choosing framework, data storage, solutions and components interaction (eg: protocol)
  - Make a desicion between speed and quality
  - Between the client and their representative (PM) and the engineering team (developer)

- Design vs Architecture
  - Software Design: look at lower-level aspects of the system
  - Software Architecture: look at the higher-level aspects of a system

- How to be a good software architect
  - Solid technical foundation
  - Communication skill and style that people like to communciate with
  - Bit of project planning and multitasking, etc
  - Look forward into the future. Quickly assess technologies and see where it fits in
  - Looks at what big company doing and read blogs, tech press
  
### Object Oriented Thinking

- Object Oriented Modelling
  - Using the concept of onject in software to represent key concept
  - Keep code organized, flexible and reusable

### Design in Software Process

- Conceptual and Techinical Design
  - Conceptual mock up: initial thoughts on how requirement will be satisfied. Major components and relations without techinical details
  - Techinical design: split component into smaller chuck that is spcific enough for techincal details
- Categories of Objects in Design
  - **Entity Objects**: object representing real-world entity. Know their attributes and modify themselves with pre-defined rule
  - **Boundary Object**: object dealing with another system/user. Such as A form input component in REACT
  - **Control Object**: object for coordination between different components. Such as "Redux" or state management object in React
  
### Design for Quality Attributes
- Software design always involve competing quality and trade-off

### Class Responsibility Collaborator (CRC)
- A method to design and orgianize software component and classes.
- CRC Break a class design into 3 compoenent: Class, its Responsibility and a Collaborator. And example
  - Class: Banking Machine
  - Reponsibility: 1. Withdraw Money 2. Deposit Money 3. Verify Identity 4. Change Password
  - Collaborator: Bank Card class, Bank Customer classx

## Module 2: Models: Bridging Concepts and Solutions

### Creating Models in Design
- Object-oriented is such a powerful design appraoch because it is easy to understand by both users and developers
- There are two space: problem space and solution space. In design, they are visited and re-visited iteratively
- Visual notation method like Unified Modelling Lanugage (UML) is very popular in design to help visiualizing the outcome and relations

### Four Design Principle
**This is different from 4 pillar of Object Oriented Programming. This is designing**
- **Abstraction**:
  - Simplify a problem
  - Extract the core concept ignoring non-essential details
  - Most common type of abstraction in Object Oriented Modeling is using Class
  - Abstraction should define the attributes of the class
  - Abstraction should also define the behaviors of a class
  - Abstraction is not a fixed creation but can change when problem change
- **Encapsulation**:
  - Bundle data and functions to a self-contained object
  - Expose interface whereby other objects can access
  - Restricted access to certain inside details
  - Keep software modulars and easy to work with black box thinking 
- **Decomposition**:
  - A process to break down a problem into smaller pieces
  - Important to understand how the parts related to whole
- **Generalization**:
  - Reduce the redundancy when solving problems
  - Commonly use in algorithm, meant to be used to perform same action on different data
  - In OOP, the concept of inheritance is a form of generalization. which factor out similarity into a common parent

### Design in UML (or Class Diagram)
  - Layout a class into 3 parts: Class name, Properties and Methods
  - While CRC card do not separate properties vs operation, UML class diagarm list out it clearly
  - Easy to convert from UML diagram to a code
  - CRC code is closed to the problem, UML is closed to code
#### UML and Design Principle
  - **Abstraction**: UML diagram list out the essential property and behavior of what class should do, without listing all the details about how they can be achieved
  - **Encapsulation**: UML diagram describe the bundle of data and their exposure. For example, a private variable is described, and exposed by a getter/setter
  - **Decomposition**: 3 relationship - association, aggregation, composition
    - association: loose relation. May interact with each other for some time. No "belong" - Student vs Sport
    - aggregation: "has-a" relation describing whole and part. One party is an important part of the other one, but they can live independently. Car vs Driver
    - composition: exclusively contained "has-a" relationship. parts rely on wholes to exist. Such as House vs Room
  - **Generalization**: UML diagram shows the inheritence by attaching arrow to the parent class
    - In JAVA, there are two ways of generalization: extends (inherit) and implements (interface)
    - for implements, use dot arrorw pointint the interface
    - Two class implementing the same interace shows the polymorphism

## Module 3
### Design Principle
- Evaluating Design Complexity with Coupling and Cohesion
  - **Coupling**: Complexity between modules and other modules. Generally, loosely coupled module to be considered a better design (Lego vs Puzzle)
    - Degree: # of connections between the module and others (Eg parameters or dependency)
    - Ease: connection should not rely on heavily understanding another module
    - Flexibility: interchangebility, should be replacable in the future should a better solution kicks in
  - **Cohesion**: Complexity within the module, clarity of reponsibility of the module. Single, clear purpose of a module representing high cohesion which is good
- Separation of Concerns is usally an ongoing process through the design
  - Individual class should be able to easily swap in and out without rewritting a large piece of code
- Information hiding: allows modules of our system to give other modules minium information class needed to function. This is usually done through encapsulation. To do this usually developer use access modifier
#### Conceptual Integrity
- Making consistent software
- Making decision of guideline so all developer follows the same pattern/developers
- For example, a decision of a naming convension
- Code review also practice conceptual intergity
- It is the most important concept of software design

### Generalization Principle
- Inheritance is usally hard to master, misusing can lead to more problem
- If subclass does not have any additional feature than superclass, it does not need to exist
- Liskov substitution principle: Subclass can only replace super class **if and only if** subclass does not change the funtionality of superclass
  - Eg: Animal class with "Walk" behavior. Whale class inherit animal class and overwrite "walk" method with "swim" behavior

### Modelling Behavior
#### Sequence Diagram
Is a maps of conversation between different class. It shows how each class communicate with other in a particular sequence
- use a box with colon to represent object/class
- use dashed-line to describe the time passing by
- use soild line arrow to show the message share between class from sender to receiver
- use dashed line arrow to show the return message
- use box to describe the alternative
- use bracketed sentence to describe the condition

#### State Diagram
- State is a way object exists at a particular moment, determined by the value of its attribuites
- Describe a single object and show the behaviors in response to other system events
- Use arrow to indicate the transition of state from one to the other
- State name: what is the status of current object - Car state: idle, driving, reverse
- State variable: the data relevant to the state - Car state variable: speed
- Activity: entry, do, exit (compare to the concept of lifecycle) 
  - solid line arrow to indicate transition activity
  - bracket indicate the condition

### Model Checking
- Verify the state of software and it does what they supposed to do
- Usually using a software to do ehte checking
- Three phase: Modelling phase, running phase, analysis phase