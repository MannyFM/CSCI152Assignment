/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.fss;

import csci152.adt.Queue;
import csci152.adt.SortedQueue;
import csci152.adt.Stack;
import csci152.impl.LinkedListStack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manny
 */
public class FileSystem implements FileSystemInterface {

  Folder root, currentFolder;
  Stack<Command> history;

  FileSystem(String rootName) {
	root = new Folder(rootName, null);
	currentFolder = root;
	history = new LinkedListStack<>();
  }

  @Override
  public void doCommand(Command cmd) {
	boolean okay = false;
	switch (cmd.getCommandCode()) {
	  case Command.MAKE_FOLDER: {
		Folder newFolder = new Folder(cmd.getName(), currentFolder);
		okay = currentFolder.addFolderOrDoucument(newFolder);
		break;
	  }
	  case Command.MAKE_DOCUMENT: {
		Document newDocument = new Document(cmd.getName(), currentFolder);
		okay = currentFolder.addFolderOrDoucument(newDocument);
		break;
	  }
	  case Command.REMOVE_EMPTY_FODLER: {
		okay = currentFolder.removeFolder(cmd.getName());
		break;
	  }
	  case Command.REMOVE_DOCUMENT: {
		okay = currentFolder.removeDocument(cmd.getName());
		break;
	  }
	  case Command.GO_UP_ONE_FOLDER: {
		if (currentFolder == root) {
		  System.out.println("Current folder is already root");
		  okay = false;
		  break;
		}
		cmd.setName(currentFolder.getName());
		currentFolder = currentFolder.getParent();
		okay = true;
		break;
	  }
	  case Command.GO_INTO_FOLDER: {
		FolderOrDocument value = currentFolder.getObject(cmd.getName());
		if (value == null || !value.isFolder()) {
		  System.out.println("No such folder");
		  okay = false;
		  break;
		}
		currentFolder = (Folder) value;
		okay = true;
		break;
	  }
	  default: {
		okay = false;
//		throw new UnsupportedOperationException("Not supported yet.");
	  }
	}
	if (okay) {
	  history.push(cmd);
	}
  }

  @Override
  public void undoLastCommand() {
	if (history.getSize() <= 0) {
	  System.out.println("No commands to undo");
	  return;
	}
	Command cmd;
	try {
	  cmd = history.pop();
	} catch (Exception ex) {
	  System.out.println("Something really bad happened " + ex);
	  return;
	}
	switch (cmd.getCommandCode()) {
	  case Command.MAKE_FOLDER: {
		currentFolder.removeFolder(cmd.getName());
		break;
	  }
	  case Command.MAKE_DOCUMENT: {
		currentFolder.removeDocument(cmd.getName());
		break;
	  }
	  case Command.REMOVE_EMPTY_FODLER: {
		Folder folder = new Folder(cmd.getName(), currentFolder);
		currentFolder.addFolderOrDoucument(folder);
		break;
	  }
	  case Command.REMOVE_DOCUMENT: {
		Document document = new Document(cmd.getName(), currentFolder);
		currentFolder.addFolderOrDoucument(document);
		break;
	  }
	  case Command.GO_UP_ONE_FOLDER: {
		FolderOrDocument value = currentFolder.getObject(cmd.getName());
		currentFolder = (Folder) value;
		break;
	  }
	  case Command.GO_INTO_FOLDER: {
		cmd.setName(currentFolder.getName());
		currentFolder = currentFolder.getParent();
		break;
	  }
	  default: {
		return;
//		throw new UnsupportedOperationException("Not supported yet.");
	  }
	}
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
