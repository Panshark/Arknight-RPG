# Arknight-RPG

## Story Introduction:
This is a Turn-Based RPG game based on Arknights. You can just use your mouse to play this game. Arknights (Chinese: 明日方舟; pinyin: Míngrì Fāngzhōu, "Tomorrow's Ark") is a free-to-play tactical RPG/tower defense mobile game developed by Chinese developer Hypergryph. It was released in China on 1 May 2019, in other countries on 16 January 2020 and in Taiwan on 29 June 2020. Arknights is available on iOS and Android platforms and features gacha game mechanics. An anime adaptation by Yostar Pictures has been announced. Leading your agents and enjoying your adventure.

## How to play:
- Add sqlite-jdbc-3.30.1.jar to your Module->Dependencies
- Run run.java;
- Hit NewGame bottom in Game menu;
- Now you have three agents, and you are in the scenes interface:
  - Click combat group to check their statuses;
  - Click bag to check your equipment, you can equip them, sell them, or discard them;
  - Click shop to buy new equipment, and repair old equipment;
  - Click lounge to buy HoneyBerry, which could heal the agents during a battle, and also you can directly heal your agents in the lounge;
  - Click the option to use save/load, and help.
- Then you could choose the chapter you want to go to. After you do that, in the combat interface, you can do: Attack, Healing, and Escape.
- You will obtain equipment after a victory, and also you will find new agents after area 0.

## Tips:
1. You have three initial agents in the very beginning. And when you finish area 0, you can challenge whatever 1-1, 2-1, 3-2. They are in the same difficulty;   
2. You will meet new agents. You have to weigh which agents you should use because you can only use three in one combat;     
3. It will randomly drop different quality equipment in each battle. The equipment can also be purchased from the store, used to enhance the character's combat effectiveness;  
4. If you can't continue the story, try repeating the previous chapters to gain more strength;
5. After the middle Areas are completed, proceed to the bottom Area for the final chapter.

## Using techniques
- GUI: Almost everywhere;
- Thread: In shop.java, data.java, base.java, and bag.java, when associated with character attributes we use threads. This is for real-time refresh of the data;
- Database: In S_L.java, it is for the save/load feature. We use creat.java to creat tabel in database.db, transform data by socket with data stream and object stream, and use server.java to listen the save and load actions.

## Environmnt
- SDK 11, language level = 11.0
- Recommend to use Intellij IDEA https://www.jetbrains.com/idea/
