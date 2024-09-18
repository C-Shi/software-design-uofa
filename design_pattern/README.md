# Design Pattern
- Practical solutions to common, recurring design problem in software
- 23 famous design pattern. Suggest reading "*Design Patterns: Element so Resuable Object-Oriented Software*"
- It is conceptual, help software developer with a guide to solve problems
- Clear, easy discussion using terms in design pattern to save time in communication

### Design Patterns Catelog
1. **Creational**: handle how you create objects.
2. **Structural**: how objects are connected to each other structurally
3. **Behavioural**: how objectes destribute works, or how independent object works together to achieve goals
- Notes that lots of design pattern are inter-catelog, which means they implement multiple catelog

### Singleton Pattern
- It is a creational design pattern
- Enforce one and only one object of a Singleton class
- Has the Singleton object globally accessible
- Implementation is diff from languages to languages but usually inovlve using static/class variable to store the unique instance

    ```python
    class Deck
        _instance = None

        def __new__(cls, *args, **kwargs):
            if cls._instance is None:
                cls._instance = super().__new__(cls, *args, **kwargs)
            return cls._instance

        def __init__(self, *args, **kwargs):
            pass
    ```

- Some example: Database Connection Pool, Software Configuration, Logging, or Gaming share state

### Factory Method Pattern
- It is a creational design pattern involving Factory class and object
- Factory Object is an object whose role is to create other objects, eg a `CarFactory` object that is used to only create different type of `Car` objects
- Cut out redundant code when multiple clients want to instantiate the same set of class

    ```python
    # Factory Object for Knife that is responsible to create Knife Object
    class KnifeFactory():
        def createKnife(self, kind):
            if kind == 'Bread Knife':
                return BreadKnife()
            else:
                return SteakKnife()

    class KnifeStore():
        def createOrder(self, kind):
            return KnifeFactory().createKnife(kind)
    ```

- Factory Object and Factory Method are slightly diffrerent. Factory Method:
  - encapsulate object creation into a method **inside** class itself
  - Each subclass provide its own implementation of creation
  - Factory Method is like an abstract method in a class or interface

    ```python
    # Creator Abstract class
    class KnifeFactory(ABC):
        # Object creation encapsulte inside of class as abstract
        @abstractmethod
        def create_knife(self):
            pass

    # Concrete Creator
    class KnifeStore(KnifeFactory):
        # each subclass has its own implementation
        def create_knife(self, shape):
            if shape == 'short':
                return ShortKnife()
            else:
                return LongKnife()
    ```

### Facade Pattern
- is a structural design pattern
- Facade do not add additional functionality, it provide entry to subsystem
- Facade is a wrapper class to encapsulate the subsystems to hide its complexity
- Facade act as encapsulation layer at system level

    ```python
    class Keyboard():
        def keydown(self, key):
            return key

    class Monitor():
        def show(self, message):
            return f"message on: {message}"
    
    class ComputerFacade():
        def __init__(self):
            self._keyboard = Keyboard()
            self._monitor = Monitor()
            self._data = ""
        def typing(self, words):
            for character in words:
                self._data += self._keyboard.keydown(character)
            return self._monitor.show(self._data)
    ```

- The above example, `ComputerFacade` is a Facade class that encapsulate the interaction of Computer system `Keyboard` and `Monitor`, providing entry point for client class to interact with Computer system
- remove the need of client class to manage subsystem. In this case, `User` class only need to interact with `ComputerFacade` rather than individual `Keyboard` or `Monitor`

### Adapter Pattern
- It is a structral design pattern
- Faciliate communication between two existings system with a compatiable interface (eg:adating new 3rd party library)
- Adapter encapsulate the adaptee and presents a new interface (Eg: a wrapper class)

    ```python
    # system that only process ascii code
    class ASCIICode():
        def save_text(self, ascii_codes):
            return f"SAVE ascii code {ascii_codes} into db"

    # adapter class that will translate pure text to ascii and hand over
    class TextASCIIAdapter():
        def __init__(self):
            self._coder = ASCIICode()

        def _adapt(self, text):
            # Convert
            ascii_codes = [ord(char) for char in text]

            self._coder.save_text(ascii_codes)
        
        def save_text(self, text):
            self._adapt(text)

    if __name__ == '__main__':
        code_translator = TextASCIIAdapter()
        # As a normal user, I only be able to type in English but not ASCII
        user_input = input("Please type your message")

        # adapter will help adapting my English to ASCII and save to system
        code_translator.save_text(user_input)
    ```

### Composite Pattern
- It is a structural design pattern containing: **Component, Leaf and Composite**
- **Component**: Abstract calss or interface that define common interface for all concrete objects and composite
- **Leaf**: Concrete class that represent end object
- **Composite**: concrete class containing collection of other leaf or composite, has to implement Component
- Both end object and object collections can be treated uniformly
- New component can be added easily by implementing Component class (eg: new animal like dog can be added to extend Animal class)
- Can build tree structures
- Composite pattern reflect polymorphism which multiple class can be implement on the same interface

    ```python
    # Component class - determine the interface to implement
    class Animal(ABC):
        @abstractmethod
        def eat(self):
            pass

    # Leaf class - actual concrete object
    class Tiger(Animal):
        def eat(self):
            print("Tiger eat meat")

    # Leaf class - actual concrete object
    class Elephant(Animal):
        def eat(self):
            print("Elephant eat plants")

    # Composite class - Class implmenting the component class, but can also hold other Leaf or sub composite class
    class Zoo(Animal):
        def __init__(self):
            self._animals = []

        def add(self, animal):
            self._animals.append(animal)
        
        def eat(self):
            for animal in self._animals:
                animal.eat()
    ```

### Proxy Pattern
- It is a structural design pattern
- provides an  placeholder object to control access to another object
- Use a simplfied object (Proxy object) to represent the real subject object
- Proxy class act as wrapper to interact with client
- Three components: **Subject(Interface)**, **RealObject**, **Proxy**
- **Both Proxy and Real Subject will implement common interface**, but proxy will delegate some method to real subject
- Three common use of proxy class
  - Act as a virtual proxy to represent a resource intensive real subject. Only create real object when needed
  - Act as protection proxy: checking the request and control access
  - Act as remote proxy where real subject class exist remotely

    ```python
    ## Example of Protection Proxy
    class DistributorSubject(ABC):
        @abstractmethod
        def ship_car(self, number):
            pass

    class Dealership(DistributorSubject):
        def __init__(self, factories):
            self._factories = factories
            self._logs = []

        def ship_car(self, number):
            for f in self._factories:
                if f.instock(number):
                    self.log_shippment(f)
                    return f.ship_car()
            raise ValueError("No enough stock")
        
        def log_shippment(self, f):
            self._logs.append(f"A shipping created from factory {f.location}")

    class Factory(DistributorSubject):
        def __init__(self, quantities, location):
            self._quantities = quantities
            self._location = location
        
        def instock(self, number):
            return self._quantities > number

        @property
        def location(self):
            return self._location
        
        def ship_car(self, number):
            print(f"{number} of cars shipped")
            self._quantities -= number

    if __name__ == '__main__':
        factory_1 = Factory(100, "Vancouver")
        factory_2 = Factory(50, "Toronto")

        dealer = Dealership([factory_1, factory_2])

        dealer.ship_car(10)
    ```

### Decorator Pattern
- It is a structural design pattern
- Dynamically extending object's functionanility without need to alternating code
- Allow multiple decoration to work with base object together or separately
- builds a behaviour by stacking objects 
- **Component**: Interface defining common operation
- **ConcreteComponent**: Base component implementing functionality
- **Decorator**: abstract class that wrap components and delegate operations
- **ConcreteDecorator**: Class that extend Decorator and add functions

    ```python
    # Component
    class AbstractVehicle(ABC):
        @abstractmethod
        def cost(self):
            pass

    # Concrete Component
    class Vehicle(AbstractVehicle):
        def cost(self):
            return 1000

    # Decorator
    class AbstractVehicleDecorator(AbstractVehicle):
        def __init__(self, vehicle: Vehicle):
            self._vehicle = vehicle
        
        def cost(self):
            return self._vehicle.cost()

    # Concrete Decorator
    class VehicleWithRoofRack(AbstractVehicleDecorator):
        def cost(self):
            return self._vehicle.cost() + 100

    # Concrete Decorator
    class VehicleWithTrailer(AbstractVehicleDecorator):
        def cost(self):
            return self._vehicle.cost() + 200

    if __name__ == '__main__':
        car = Vehicle()
        print(car.cost()) # 1000

        car_with_roof_rack = VehicleWithRoofRack(car)
        print(car_with_roof_rack.cost()) # 1100

        car_with_trailer_and_roof_rack = VehicleWithTrailer(car_with_roof_rack)
        print(car_with_trailer_and_roof_rack.cost()) # 1300
    ```
- The code above shows the decorator pattern that dynamically add or remove features of a car, and adjust the price accordingly
- This also make easy to work with **single inheritance** languages

### Template Method Pattern
- A behavior design pattern
- define skeletons of an algorihm in base class, let subclass override specific step implementation
- Subclass can change the implementation but not the structure
- `woodworking` is a template method, which define sturcutre
- `_cutting()` is abstract method allow subclass to modify implementation
  
    ```python
    class WoodWorking(ABC):
        def woodworking(self):
            self._cutting()
            self._fastening_()
            self._sanding()

        def _fastening(self):
            print("fast with screw")

        def _sanding(self):
            print("sand with sander")

        @abstractmethod
        def _cutting(self):
            pass

    class WoodCrafting(WoodWorking):
        def __init__(self):
            super().__init__()

        def _cutting(self):
            print("cutting with jigsaw")

    class CabinetMaking(WoodWorking):
        def __init__(self):
            super().__init__()
        
        def _cutting(self):
            print("cuttingwith circular saw")

    if __name__ == '__main__':
        cabient = CabinetMaking()
        cutting_board = WoodCrafting()
    ```

### Chain of Responsibility Pattern
- A behavioral pattern
- allow passsing request through a chain of handler until it get handled or end
- decouple the sender of reqeust from receiver. Sender do not need to know who eventually handle it
- **Handler**: Interface to declare method for handling, and reference next handler
- **Concrete Handler**: actual class implementing the handling
- Common case: Error Handling Chain, Corporate Request approval process

    ```python
    class Approver(ABC):
        def __init__(self, supervisor : Approver):
            self._role = None
            self._supervisor = supervisor

        def approve(self, request):
            if self._is_authorized(request):
                return f"request is fullfilled by {self._role}"
            else if self._supervisor:
                self._supervisor.approve()
            else:
                raise ValueError("Cannot fullfill request")
            
        @abstractmethod
        def _is_authorized(self, request):
            pass

    class Manager(Approver):
        def __init__(self):
            super().__init__()
            self._role = "Manager"

        def _is_authorized(self, request):
            return request < 10

    class Director(Approver):
        def __init__(self):
            super().__init__()
            self._role = "Director"

        def _is_authorized(self, request):
            return request < 20

    class President(Approver):
        def __init__(self):
            super().__init__()
            self._role = "President"

        def _is_authorized(self, request):
            return request < 30

    if __name__ == '__main__':
        p = President()
        d = Director(p)
        m = Manager(d)

        request_1 = 5
        m.approve(request_1) # request is fullfilled by manager

        request_2 = 15
        m.approve(request_2) # request is fullfilled by dirctor
        
    ```

### State Pattern
- A behavior design pattern
- change the behavior of object based on its state at runtime
- It encapsulate state-specific behavior within state class, and separate the concern
- Provide clean way of state transitions
- **Context**: The acting object, who maintains the current state and delegate state-specific behavior
- **State**: Interface of Abstract class that define the method each state should have
- **ConcreteState**: State class implementing interface and hold behaviors

    ```python
    from abc import ABC, abstractmethod
    # State Inteface
    class OrderStateInterface(ABC):
        @abstractmethod
        def complete(self, context):
            pass

    # Concrete state holding behaviors
    class PlacedState(OrderStateInterface):
        def complete(self, context):
            print("Move Order to Shipment")
            context.set_state(ShippedState())

    # Concrete state holding behaviors
    class ShippedState(OrderStateInterface):
        def complete(self, context):
            print("Move Order to Delivered")
            context.set_state(DeliveredState())

    # Concrete state holding behaviors
    class DeliveredState(OrderStateInterface):
        def complete(self, context):
            print("Order Delivered")

    # Context class maintaining curretnt state and deligate behavior actions
    class Order:
        def __init__(self):
            self.state = PlacedState()

        def set_state(self, state):
            self.state = state

        def proceed(self):
            self.state.complete(self)
    
    if __name__ == '__main__':
        order = Order()

        order.proceed()
        order.proceed()
    ```

### Command Pattern
- A behavior design patter that encapsulate a request a object
- Store and schedule different requests
- Allow undo and redo of the commands

    ```python
    # Command Interface
    class Command(ABC):
        @abstractmethod
        def execute(self):
            pass

        @abstractmethod
        def unexecute(self):
            pass

    # ConcreteCommand - Concreate command take a receiver object to call against
    class SendCommand(Command):
        def __init__(self, receiver):
            self._receiver = receiver
        
        # Concrete command only provide instruction, (like a memo from boss) but do not actually doing the work
        def execute(self):
            self._receiver.do()

        def unexecute(self):
            self._receiver.undo()

    # Receiver
    class Message:
        def __init__(self, data):
            self._data = data

        # Receiver object is the one doing the actual work (Like hard-working middle class in a company)
        def do(self):
            print(f"{self._data} sent")
        
        def undo(self):
            print("message recalled")

    
    # Invoker
    class WeChat:
        def __init__(self):
            self.command = None

        # Invoker do not need to know the detail about command, it is a faciliator like secretary in a company
        def set_command(self, command):
            self.command = command

        def press_button(self):
            self.command.execute()

        def long_press(self):
            self.command.unexecute()

    # Client
    if __name__ == '__main__':
        # Create Receiver
        message = Message("Hello World")

        # Create Concrete Command and linked to Receiver
        sent_command = SendCommand(message)

        # Create Invoker
        app = WeChat()

        # Invoke forward command
        app.set_command(sent_command)
        app.press_button()

        # Invoke backword command
        app.long_press()
    ```
- Using a company structure analogy:
  - **Boss = Client** that issue command
  - **Memo = Command/ConcreteCommand** describe what need to be done
  - **Secretary == Invoker** Managing and excuting the command without knowing detail
  - **Labor == Receiver** perform the actual work

### Mediator Pattern
- A behavior pattern promote loose coupling between objects
- A mediator object sit between two real object as faciliator/communicator
- Each object talk to others indrectly via a mediator
- Adding a new connection or change existing connection is relatively easy
- An exmaple would be a home IOT network that has a controller
- **Mediator** and **Colleague**

    ```python
    # Meidator faciliating every message
    class Controller:
        def __init__(self, nodes):
            self._nodes = nodes

        def attach(self, node: Node):
            node.link(self)
            self._nodes.append(node)

        def send(self, message: str, sender: Node):
            for n in self._nodes:
                if n.id != sender.id:
                    n.receive(message)
    # Colleague
    class Node:
        def __init__(self):
            self.id = uuid()
            self._mediator = None
        
        def link(self, mediator):
            self._mediator = mediator

        def send(self, message: str):
            if self._mediator:
                self._mediator.send(message, self)

        def receive(self, message):
            print(message)

    if __name__ == '__main__':
        controller = Controller()
        # Concrete Colleage
        camera = Node()
        alarm_bedroom = Node()
        alarm_living = Node()

        controller.attach(camera)
        controller.attach(alarm_living)
        controller.attach(alarm_bedroom)

        camera.send("Motion Detected")
        # Print "Motion Detected" twice
    ```

### Observer Pattern
- A behavior pattern define one-to-many relationships
- Subject and Observer work together.
- Observer subscribe to Subject. When Subject change, it notify all subscriber. Observer receive notification will update itself
- **Subject** need: register, unregister and nofity method
- **Observer** need: update method

    ```python
    # Subject that maintain a list of observer and call notify when needed
    class Subject:
        def __init__(self):
            self._observers = []

        def register(self, observer):
            self._observers.append(observer)

        def unregister(self, observer):
            self._observers.remove(observer)

        def notify(self, message):
            for observer in self._observers:
                observer.update(message)

    # Observer subscribe to Subject and will get notify and update themself
    class Observer:
        def update(self, message):
            print(message)

    if __name__ == "__main__":
        weather_app = Observer()
        weather_sbj = Subject()
        weather_sbj.register(weather_app)

        weather_sbj.notify("28 C") # print out 28C
    ```