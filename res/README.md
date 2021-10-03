![Jungle Sanctuary](https://www.goeco.org/_media/media/1299/13972.jpg)

  <p align="center">
    <strong>Welcome to Jungle Sanctuary!!!</strong>
</p>
<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#list-of-features">List Of Features</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#how-to-run">How to Run</a></li>
        <li><a href="#installation">How to Use Program</a></li>
      </ul>
    </li>
    <li><a href="#Description">Description of Examples</a></li>
    <li><a href="#assumptions">Assumptions</a></li>
    <li><a href="#limitations">Limitations</a></li>
     <li><a href="#design-and-model-changes">Design and Model Changes</a></li>
    <li><a href="#citations">Citations</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

**Jungle Sanctuary** is a beautiful urban wildlife refuge housing primates of various species (mostly monkeys). While nothing beats actually being there, here is a project to help you experience how a wildlife sanctuary functions.
Everything from welcoming the primates to performing medical check, from allocating housing units to shopping for the amazing beings housed, it's all here. Let's dive in.

The journey of a primate in brief is as follows:
* First the incoming primate is welcomed to the Sanctuary.
* The first action item is to send the primate to isolation where its medical examination is performed.
* All the physical and personal characteristics like age, weight, gender, favorite food, specie type are documented and stored in the isolation unit.
* Once the primate clears medical exam, he/she is sent to enclosure unit where the primate can live happily along with other similar animals.
* The journey involves lot of backend process like checking availability in isolation and enclosures and performing medical tests. All the feature of the Sanctuary are listed in the [features](#list-of-features) section.

<!-- List of Features -->
## List of Features
The project Jungle Sanctuary provides following features/benefits to the user:
* Provides the list on all the primates housed in the sanctuary at point of time along with the details of their present location.
* Provides user the flexibility to look up for primates belonging to a particular type of species as to where they are currently housed.
* The project auto generates the list for food items with the quantity to be shopped based on every primate's favorite food choice and their size.
* Initially the Sanctuary starts with a fixed number of isolation and enclosure units, but the project provides flexibility to expand these units when there is sufficient funding support.
* It allows provides user the functionality to create signs for a particular Enclosure Unit. 
  The Sign would include primates housed in that enclosure along with their name,gender and favorite food.These signs would be helpful for the visiors to know about the inhabitants.__

<!-- Getting Started -->
## Getting Started

### How to Use

The folder structure of the project basically contains three directories.

* src directory contains all the interfaces and the classes.
* test directory contains all the test cases for every class's public methods.
* res directory contains docs, jar file, and manifests

User can <a href="#run-the-jar"> run the jar file </a> directly or use the driver class to execute the project.

<!-- run-the-jar -->
### How to Run

<strong><u> Running Using A Jar File </u></strong>

To run the jar file use the following command in the terminal.
```
java -jar <jarName> 
```
The jar file added in the res folder is name Monkey_Project.jar .

<strong> <u> Running Driver Class </u> </strong>

Navigate to src/driver directory.
You will find a **sanctuaryDriver** class with main method in it. Right click and Run the program.

<!-- Description of Examples-->
## Description of Examples
As a sample dry run, I have executed the program twice. 

<b><i> Run 1 (Run_1.txt file can be found in res folder) </i></b>

In this run the following steps are executed:
1. The Jungle Sanctuary is created with 15 isolations and 20 enclosures. (using JungleSanctuary primateSanctuary = new Animal( 15,20);)
2. 10 different primates are added to the sanctuary. ( Example : primateSanctuary.addNewMonkey("Paula", Species.HOWLER, 10, 20,
   FavoriteFood.EGGS, Sex.FEMALE, 5);
   
    
    where Paula is name - string value, Species.HOWLER represents the type of monkey, 10 is age of the monkey - int, 20 is weight of the monkey,
    FavoriteFood.EGGS is the favorite food of the monkey, Sex.FEMALE represents the gender and 5 denotes the size)
  
3. **Note : Do refer the Enumarations - Sex,FavoriteFood and Species in the src folder before providing input.Only certain values are allowed***
4. All the primates are by-default sent to isolation.
5. After the health checkup, one of the monkey's health updated as unhealthy. <i>(primateSanctuary.updateMedicalHealthOfMonkey(<Monkey>, false);)</i>
6. All the healthy monkeys are then sent to enclosures.<i>(primateSanctuary.sendMonkeyToEnclosure(Monkey);)</i>
7. The list of monkeys currently housed in the sanctuary is printed after sorting alphabetically by name. <i>(primateSanctuary.getAllMonkeysHoused();)</i>
8. In the list we can notice one of the monkey is in isolation and 9 are in enclosures. They have housingID's assigned according to the business logic.
9. The shopping list is printed for all the favorite foods along with the quantity required.<i>(primateSanctuary.getShoppingList())</i>
10. We can look up for monkeys belonging to particular species. <i>(primateSanctuary.lookUpSpecies(Species.HOWLER)) </i>
11. Signatures for species housed in an enclosure is also generated.<i> (primateSanctuary.produceSign(<enclosureID>);) </i>
12. The list sorted according to all the species housed in sanctuary is also generated in the end. <i>(primateSanctuary.getSpeciesList())</i>

<b><i> Run 2 (Run_2.txt file can be found in res folder) </i></b>

1. In run two extra functionalities and corner cases are executed. 
2. Increasing isolation 
3. Increasing enclosures 
4. Transferring primates from enclosures to isolations.
5. Sending monkey to isolation when it is full to capacity.
6. Sending monkey to enclosure when there is no space in enclosures.(ideally transfer to new sanctuary).

The current driver class has code for execution of Run2.

<!-- Assumptions -->
## Assumptions
To successfully implement the project, the following assumptions have been made 
1. The area of each enclosure is assumed to be same.
2. The primate is not allowed get in the sanctuary if the there is no space in is~~~~olation units.
3. Assumed that primates of different types won't be housed together in enclosures.
4. If enclosures are full, primate will be transferred to other sanctuaries. 

<!-- Limitations -->
## Limitations
1. The jungle sanctuary currently only houses monkeys, but can be extended to house any type of primates.
2. A centralised Database is not used yet, which will be a feature in coming days to have better storage system even when monkey is transferred 
to other sanctuaries.

<!-- Design and Model Changes -->
## Design and Model Changes
* There were major refactor changing in the design version 1 as compared to design version 2.
* Earlier single entity classes like isolation,enclosure, monkey had certain attributes which represent a group of the entity which has been eliminated now.
* The UML diagram in PROJECT 1 DESIGN MEETING.pdf represents version 1 and the UML diagram in PROJECT_1_v2 represents version 2. These PDFs are available in res directory.

<!-- Citations -->
## Citations
1. [Using Comparator](https://www.tutorialspoint.com/java/java_using_comparator.htm)
2. [JavaDoc for Using ID generator](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html)

<!-- Contact -->
## Contact
```
Name : Rishita Reddy
Email : reddy.ri@northeatern.edu
Project Link: https://github.com/RishrReddy/Monkey-Project.git
```