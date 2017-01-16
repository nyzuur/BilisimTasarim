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
public class InstructionWidthAndAddressingMode {

	/**
	 * @param args
	 */
	public byte ProgramLength;
	
	public byte AddressingMode;
	
	
	public InstructionWidthAndAddressingMode(byte length,byte mode){
		
		this.ProgramLength=length;
		this.AddressingMode=mode;
	}
}
