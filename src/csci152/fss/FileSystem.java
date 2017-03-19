/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.fss;

import csci152.adt.Queue;
import csci152.adt.SortedQueue;

/**
 *
 * @author manny
 */
public class FileSystem implements FileSystemInterface {

  Folder root, currentFolder;

  FileSystem(String rootName) {
	root = new Folder(rootName, null);
	currentFolder = root;
  }

  @Override
  public void doCommand(Command cmd) {
	switch (cmd.getCommandCode()) {
	  case Command.MAKE_FOLDER:
		Folder newFolder = new Folder(cmd.getName(), currentFolder);
		currentFolder.addFolderOrDoucument(newFolder);
		break;
	  case Command.MAKE_DOCUMENT:
		Document newDocument = new Document(cmd.getName(), currentFolder);
		currentFolder.addFolderOrDoucument(newDocument);
		break;
	  case Command.GO_INTO_FOLDER:
		break;
	  default:
		throw new UnsupportedOperationException("Not supported yet.");
	}
  }

  @Override
  public void undoLastCommand() {
	throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void listContents() {
	SortedQueue<String> q = currentFolder.getContentNames();
	String res = "> " + currentFolder.getName() + "\n";
	try {
	  while (q.getSize() > 0) {
		res += ("\t" + q.dequeue() + "\n");
	  }
	} catch (Exception ex) {
	  System.out.println("Something bad happened " + ex);
	}
	System.out.println(res);
  }

  @Override
  public Queue<String> getAllPaths() {
	return root.getAllPaths();
  }

}
