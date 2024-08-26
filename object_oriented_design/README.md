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