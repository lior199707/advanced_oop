# Mermaid something

Let's go!
```mermaid
classDiagram
    class EFoodType {
        <<enumeration>>
        MEAT
        NOTFOOD
        VEGETABLE
    }
    class IEdible {
        <<interface>>
        +getFoodType() EfoodType
    }
    class IDiet {
        <<interface>>
        +canEat(EFoodType food) boolean
        +eat(Animal animal, IEdible food) double
    }
    class ILocatable {
        <<interface>>
        +getLoctation() Point
        +setLocation(Point) boolean
    }

    class Mobile {
        -location:Point
        -totalDistance:double
        +Mobile(Point) Mobile
        -addTotalDistance(double) void
        +calcDistance(Point) double
        +move(Point) double
    }

    class Point {
        -MAX_X: int
        -MAX_y: int
        -x: int
        -y: int
    }

    class Animal {
        -name: String
        -weight: double
        -diet: IDiet
        +Animal(String,Point) Animal
        makeSound()* void
        eat(IEdible)* boolean
    }

    class Lion {
        -scarCount: int
        +Lion(String)
        -roar() void
    }

    class Bear {
        -furColor: String
        +Bear(String) Bear
        -roar() void
    }

    class Elephant {
        -trunkLength: double
        -chew() void
    }

    class Giraffe {
        -neckLength: double
        -chew() void
    }

    class Turtle {
        -age: int
        -chew() void
    }

    class ZooActions {
        eat(Object animal, IEdible food)$ boolean
        +move(Object animal, Point point)$ boolean
        +main()$
    }



    IDiet <|-- Carnivore
    IDiet <|-- Omnivore
    IDiet <|-- Herbivore
    IEdible <|-- givenPlant
    ILocatable <|-- givenPlant
    givenCabbage --|> givenPlant
    givenLettuce --|> givenPlant
    Animal --|> Mobile
    IEdible <|-- Animal
    Lion --|> Animal
    Bear --|> Animal
    Elephant --|> Animal
    Giraffe --|> Animal
    Turtle --|> Animal


```


<<>> indicator to interface/enumeration and not a class.<br>
 '+' is public.<br>
 '-' is private.<br>
 '#' is protected. <br>
inheritance is indicated by Class A <|-- Class B
aggregation is indicated by Class A o-- Class B
composition is indicated by Class A *-- Class B
