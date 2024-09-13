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