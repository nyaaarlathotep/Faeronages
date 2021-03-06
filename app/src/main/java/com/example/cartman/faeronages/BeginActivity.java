package com.example.cartman.faeronages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.cartman.faeronages.game.ActivityCollector;
import com.example.cartman.faeronages.game.Adventure;
import com.example.cartman.faeronages.game.Character;
import com.example.cartman.faeronages.game.faiths;
import com.example.cartman.faeronages.game.jobs;
import com.example.cartman.faeronages.game.maps.barrenPlain;
import com.example.cartman.faeronages.game.maps.beginnersGuide;
import com.example.cartman.faeronages.game.maps.fungalWastes;
import com.example.cartman.faeronages.game.maps.limbo;
import com.example.cartman.faeronages.game.maps.mechanus;
import com.example.cartman.faeronages.game.maps.restingYards;
import com.example.cartman.faeronages.game.maps.shore;
import com.example.cartman.faeronages.game.maps.slum;
import com.example.cartman.faeronages.game.races;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BeginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);


        Button buttonStart =(Button) findViewById(R.id.buttonStart);
        Button buttonOptions=(Button)findViewById(R.id.buttonOptions);
        Button buttonQuit=(Button)findViewById(R.id.buttonQuit);
        final Button buttonAchieve=(Button)findViewById(R.id.buttonAchieve);

        buttonStart.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v){
            if(Character.getHasChecked()){
                Intent intent=new Intent(BeginActivity.this,eventlogAndClock.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(BeginActivity.this, Roll.class);
                startActivity(intent);
            }
        }
    });
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent=new Intent(BeginActivity.this,options.class);
                startActivity(intent);
            }
        });
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ActivityCollector.finishAll();
            }
        });
        buttonAchieve.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                load();
                Intent intent=new Intent(BeginActivity.this,eventlogAndClock.class);
                startActivity(intent);
            }
        });

    }
    private void load(){
        Log.d("load!!","!");
        SharedPreferences preferences=getSharedPreferences("data",MODE_PRIVATE);
        Character.setName(preferences.getString("name","Bhaal"));
        String race=preferences.getString("race","human");
        switch (race){
            case"human":{
                Character.setRace(races.human);
                break;
            }
            case"halfLing":{
                Character.setRace(races.halfLing);
                break;
            }
            case"halfOrc":{
                Character.setRace(races.halfOrc);
                break;
            }
            case"dwarves":{
                Character.setRace(races.dwarves);
                break;
            }
            case"elf":{
                Character.setRace(races.elf);
                break;
            }
            case"gnome":{
                Character.setRace(races.gnome);
                break;
            }
        }
        String job=preferences.getString("job","fighter");
        switch (job){
            case"rogue":{
                Character.setJob(jobs.rogue);
                break;
            }
            case"sorcerer":{
                Character.setJob(jobs.sorcerer);
                break;
            }
            case"cleric":{
                Character.setJob(jobs.cleric);
                break;
            }
            case"druid":{
                Character.setJob(jobs.druid);
                break;
            }
            case"bard":{
                Character.setJob(jobs.bard);
                break;
            }
            case"fighter":{
                Character.setJob(jobs.fighter);
                break;
            }
            case"paladin":{
                Character.setJob(jobs.paladin);
                break;
            }
        }
        Character.setAge(Integer.toString(preferences.getInt("age",18)));
        Character.setLevel(preferences.getInt("level",0));
        String faith=preferences.getString("faith","Pelor");
        switch (faith){
            case"Boccob":{
                Character.setFaith(faiths.Boccob);
                break;
            }
            case"CorellonLarethian":{
                Character.setFaith(faiths.CorellonLarethian);
                break;
            }
            case"Heironeous":{
                Character.setFaith(faiths.Heironeous);
                break;
            }
            case"StCuthbert":{
                Character.setFaith(faiths.StCuthbert);
                break;
            }
            case"Olidammara":{
                Character.setFaith(faiths.Olidammara);
                break;
            }
            case"Pelor":{
                Character.setFaith(faiths.Pelor);
                break;
            }
        }
        Character.check();
        Log.d("check  ","helmet checked");
        if(!preferences.getString("helmet","").equals("")){
            Log.d("loaded helmet",preferences.getString("helmet",""));
            Character.setHelmet(preferences.getString("helmet",""));
        }
        if(!preferences.getString("breastPlate","").equals("")){
            Character.setHelmet(preferences.getString("breastPlate",""));
        }
        if(!preferences.getString("leftHand","").equals("")){
            Character.setHelmet(preferences.getString("leftHand",""));
        }
        if(!preferences.getString("legArmor","").equals("")){
            Character.setHelmet(preferences.getString("legArmor",""));
        }
        if(!preferences.getString("rings","").equals("")){
            Character.setHelmet(preferences.getString("rings",""));
        }
        if(!preferences.getString("neckLace","").equals("")){
            Character.setHelmet(preferences.getString("neckLace",""));
        }

        Character.setStr(preferences.getInt("str",10));
        Character.setCon(preferences.getInt("con",10));
        Character.setCha(preferences.getInt("cha",10));
        Character.setIntll(preferences.getInt("intll    ",10));
        Character.setDex(preferences.getInt("dex",10));

        Set<String> temp=new HashSet<>();
        temp=preferences.getStringSet("spells", temp);
        String[] sp=temp.toArray(new String[7]);
        for(int i=0;i<sp.length;i++){
            if(sp[i]==null){
                sp[i]="";
            }
        }

        Character.setSpells(sp);
        ArrayList<String> missionList=new ArrayList<>();
        temp=preferences.getStringSet("mission",temp);
        String[] missions=temp.toArray(new String[5]);
        for(int i=0;i<missions.length;i++){
            if(missions[i]!=null){
                missionList.add(missions[i]);
            }
        }
        Character.setMissionList(missionList);

        Character.setGold(preferences.getFloat("gold",0));
        String map=preferences.getString("adventure","beginnersGuide");
        switch (map){
            case "barrenPlain":{
                Character.setNextAdventure(new Adventure(new barrenPlain()));
            }
            case "beginnersGuide":{
                Character.setNextAdventure(new Adventure(new beginnersGuide()));
            }
            case "fungalWastes":{
                Character.setNextAdventure(new Adventure(new fungalWastes()));
            }
            case "limbo":{
                Character.setNextAdventure(new Adventure(new limbo()));
            }
            case "mechanus":{
                Character.setNextAdventure(new Adventure(new mechanus()));
            }
            case "restingYards":{
                Character.setNextAdventure(new Adventure(new restingYards()));
            }
            case "shore":{
                Character.setNextAdventure(new Adventure(new shore()));
            }
            case "slum":{
                Character.setNextAdventure(new Adventure(new slum()));
            }
            int[] strengthLevel=new int[7];
            strengthLevel[0]=preferences.getInt("strengthenLevel0",0);
            strengthLevel[1]=preferences.getInt("strengthenLevel1",0);
            strengthLevel[2]=preferences.getInt("strengthenLevel2",0);
            strengthLevel[3]=preferences.getInt("strengthenLevel3",0);
            strengthLevel[4]=preferences.getInt("strengthenLevel4",0);
            strengthLevel[5]=preferences.getInt("strengthenLevel5",0);
            strengthLevel[6]=preferences.getInt("strengthenLevel6",0);
            Character.setStrengthLevel(strengthLevel);
        }
    }

}
