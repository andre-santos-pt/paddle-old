package pt.iscte.paddle.interpreter.impl;

import java.util.Arrays;
import java.util.Scanner;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.IExecutionData;
import pt.iscte.paddle.model.IModule;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IType;

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
