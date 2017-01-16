/*
 * Copyright 2011-2017 Niyazi UÐUR

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
import java.util.HashMap;


public class ParsedInstruction {
	public int instructionType;
	public byte addressingMode;
	public byte programLength;
	public byte usingAcc;
	
	
	
	public static ParsedInstruction parse(byte IC){
		 ParsedInstruction mParsedInstruction = new ParsedInstruction();
		 HashMap<String, Byte> BaseICs = new HashMap<>();
			BaseICs.put("LDA", (byte) 0x86);
			BaseICs.put("SUB", (byte) 0x80);
			BaseICs.put("INC", (byte) 0x4C);
	 //switch instructiontype (lda, sub, inc)
			byte subs= (byte) ((IC)%16);
	 switch(subs){
	 
	 case 0://SUB
		 mParsedInstruction.instructionType=1;
		 break;
	 case -10://LDA
		 mParsedInstruction.instructionType=2;
		 break;
	 case 12://INC
		 mParsedInstruction.instructionType=3;
		 break;
	 default: 
		 mParsedInstruction.instructionType=0;//invalid value for aftercheck
		 break;
	 }
	 byte distance;
	 //switch addressing mode(immediate, direct, indexed, extended, implied)
	 switch(mParsedInstruction.instructionType){
	
	 case 1://SUB
		 distance = (byte) ((IC - BaseICs.get("SUB"))& 0xFF);
		 if( (int)distance >= (int)0x40 && (int)distance < (int) 0x80){//using B accumulator
			mParsedInstruction.usingAcc=Proje.ACCB;
		 	distance -= 0x40;
		 	mParsedInstruction.addressingMode=distance;
		 }else if( (int)distance < (int)0x40 && (int) distance >= (int)0x00){//using A accumulator
			 mParsedInstruction.usingAcc= Proje.ACCA;
			 mParsedInstruction.addressingMode=distance;
		 }
		 else{//invalid values for aftercheck
			 mParsedInstruction.usingAcc= 0x50;
			 mParsedInstruction.addressingMode = 0x50;
		 }
		 
		 break;
	 case 2://LDA
		 distance = (byte) ((IC - BaseICs.get("LDA"))& 0xFF);
		 if( (int)distance >= (int)0x40 && (int)distance < (int) 0x80){//using B accumulator
			mParsedInstruction.usingAcc=Proje.ACCB;
		 	distance -= 0x40;
		 	mParsedInstruction.addressingMode=distance;
		 }else if( (int)distance < (int)0x40 && (int) distance >= (int)0x00){//using A accumulator
			 mParsedInstruction.usingAcc= Proje.ACCA;
			 mParsedInstruction.addressingMode=distance;
		 }
		 else{//invalid values for aftercheck
			 mParsedInstruction.usingAcc= 0x50;
			 mParsedInstruction.addressingMode = 0x50;
		 }
		 
		 break;
	 case 3://INC
		 distance = (byte) ((IC - BaseICs.get("INC"))& 0xFF);
		 if( distance == 0x20 || distance == 0x30 ){
			 mParsedInstruction.addressingMode = distance; 
			 mParsedInstruction.usingAcc= 0x50; // for extended or indexed addressing usingAcc value is not necessary. so, invalid value setted
			 
		 }else if(distance == 0x10){
			 mParsedInstruction.addressingMode=Proje.adModImplied;
			 mParsedInstruction.usingAcc= Proje.ACCB;
		 }else if(distance == 0x00){
			 mParsedInstruction.addressingMode=Proje.adModImplied;
			 mParsedInstruction.usingAcc= Proje.ACCA;
		 }
		 break;
	 default:
		mParsedInstruction.addressingMode= 0x70;//invalid value for aftercheck
			
	 
	 
	 }
	 return mParsedInstruction;
		
	}

}
