package pt.iscte.paddle.machine.impl;

import java.util.Arrays;
import java.util.Scanner;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IModule;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.IExecutionData;

public class InteractiveMode {

	private IModule program;

	public InteractiveMode(IModule program) {
		this.program = program;
	}


	public void start() {
		ProgramState state = new ProgramState(program);
		Scanner keyboard = new Scanner(System.in);
		String cmd = "";
		do {
			System.out.print("> ");
			cmd = keyboard.nextLine();
			String[] parts = cmd.split("\\s+");
			IType[] paramTypes = new IType[parts.length-1];
			for(int i = 0; i < paramTypes.length; i++)
				paramTypes[i] = IType.INT;

			if(!cmd.equals("exit")) {
				IProcedure p = program.resolveProcedure(parts[0], paramTypes);
				if(p == null)
					System.out.println("procedure not found");
				else {
					IExecutionData data = null;
					try {
						data = state.execute(p, Arrays.copyOfRange(parts, 1, parts.length));
					} catch (ExecutionError e) {
						e.printStackTrace();
					}
					System.out.println(data);
				}
			}
		}
		while(!cmd.equals("exit"));
		keyboard.close();
	}
}
