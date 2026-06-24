# FIT2099 Assignment (Semester 2, 2025)
```                                                                             
`7MMF'     A     `7MF'`7MMF'`7MN.   `7MF'MMP""MM""YMM `7MM"""YMM  `7MM"""Mq.  
  `MA     ,MA     ,V    MM    MMN.    M  P'   MM   `7   MM    `7    MM   `MM. 
   VM:   ,VVM:   ,V     MM    M YMb   M       MM        MM   d      MM   ,M9  
    MM.  M' MM.  M'     MM    M  `MN. M       MM        MMmmMM      MMmmdM9   
    `MM A'  `MM A'      MM    M   `MM.M       MM        MM   Y  ,   MM  YM.   
     :MM;    :MM;       MM    M     YMM       MM        MM     ,M   MM   `Mb. 
      VF      VF      .JMML..JML.    YM     .JMML.    .JMMmmmmMMM .JMML. .JMM.
```

Contribution Log Link: https://docs.google.com/spreadsheets/d/1oX6jqnUVR0a0dpvEsl9jClG-8xcurkCpfvVJvy_VQuA/edit?usp=sharing

A3 (Req 3) scenario:
  In this requirement there will be 3 items which can be wearable. 
  This item call Armor, it has 3 type of Armor, leather Armor which can help to block damage of 2 hits and have cold resistance
  , iron Armor block 5 hits,
  diamond Armor 10 hits and have immune to all status effect, when actor who has wear on it attacked by another actor.

  In addition, when an actor wearing any type of Armor successfully blocks more damage than received (that is, the Armor’s block 
  value exceeds the incoming damage), the excess block amount will be converted into healing, allowing the actor to recover 
  hitpoints equal to the remaining block value.

  An actor only can have one Armor at a time and it is not destroyable

A4 (Req 4) scenario:
  In this requirement when actor defeat animal it will drop different diamond.
  Diamond has, green diamond worth 5, blue diamond worth 10, red diamond worth 20.
  When actor collect the diamond inside his invenroty, the actor will has behaviour to combine diamond,
  2 green diamond merge to 1 blue diamond and 2 blue diamond merge to 1 red diamond.

A3 (Req 5) scenario:
  The Mysterio Store will spawn if the floor contains Dimensional Ground. Dimensional Ground is created when a Dimensional Bottle is 
  thrown onto the floor. When the bottle breaks, the place of the choosen locatiobn (where the bottle throw to)
  normal ground are consumed and transformed into Dimensional Ground. After 3 turns, the Dimensional Ground will cause a Mysterio 
  Store to appear. The store remains active for 3 turns before disappearing. Inside the Mysterio Store the actor can enter the store 
  once it appears. Inside the store there have a seller (sationary seller) and sell anything (custom) including the armor 
  that when actor buy it will directly apply into actor (equivalent to wear it) and if actor originally has an armor it will replace 
  the old one. From the store actor can go back to original map with a random chosen location within the original map.

  Additional information: the dimensional bottle when smash onto the ground will turn the ground into dimensional ground, this ground
  will randomly spwan one of three diffenrent type of mysterio store, each type of store offers different items and has its own unique 
  seller. The thing sell in store is depend on what the seller sell.

  The system only will have 3 different store u can decide what to sell in it but one of the store must sell armor. There won't be
  any other store except this 3 in the system. Hint: U may store the type of the store inside the dimensional ground.



  UnitTest instructions:
  FOR ALL requirement:
    Right click src/test/java then select Run 'Test in'java'' or 
    Ctrl + Shift + F10 (when navigate into a single test case file)
    it should display 'Tests passed'



How to Run the Game (with API Key)

This system integrates the Mysterio Store with an external random-number service to generate dynamic diamond prices. To ensure security and portability, the design requires the use of an API key stored in the local environment rather than embedded in the source code. The following paragraph describes the configuration process and execution procedure in a formal manner.

Before execution, the environment must include JDK 17 or higher and an IDE such as IntelliJ IDEA, which provides full compatibility with the project’s structure. The main entry point of the application is game.Application. The random pricing mechanism depends on an API key obtained from Randommer.io, which serves as the system’s external random-number provider. Users must create an account on Randommer.io, access the Dashboard → API Keys section, and generate a new key for their account. This key authorises access to the random-number endpoint and allows the game to retrieve random diamond values for each store transaction.

For security reasons, the API key should be stored as an environment variable named RANDOMMER_KEY. The configuration can be done through the system terminal or the IDE’s runtime settings. On macOS or Linux, this can be set using the command export RANDOMMER_KEY="your-api-key-here", while on Windows PowerShell it can be configured using [Environment]::SetEnvironmentVariable("RANDOMMER_KEY","your-api-key-here","User"). Within IntelliJ IDEA, the variable is set under Run → Edit Configurations → Environment Variables. This ensures that the key remains private and is never committed to the Git repository.

During execution, the program automatically retrieves the API key through the statement

String apiKey = System.getenv("RANDOMMER_KEY");


If the key is unavailable or the external service fails to respond, the system activates a local random generator as a fallback mechanism, allowing the game to remain functional even without internet access.

After successful configuration, the program can be executed directly within IntelliJ IDEA or from the command line by compiling and running the main class. The system will generate randomised diamond prices for each seller in the Mysterio Store, demonstrating the integration between in-game logic and the external API service. To maintain confidentiality, the API key must never appear in any committed source file or configuration shared through the repository. If the key is exposed accidentally, it should be revoked immediately on the Randommer.io dashboard and replaced with a new one.