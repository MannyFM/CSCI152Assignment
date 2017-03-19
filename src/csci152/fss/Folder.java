package csci152.fss;

import csci152.adt.Queue;
import csci152.adt.SortedQueue;
import csci152.impl.LinkedListQueue;
import csci152.impl.LinkedListSortedQueue;

public class Folder extends FolderOrDocument {

  private Queue<FolderOrDocument> contents;

  public Folder(String name, Folder parent) {
	super(name, parent);
	contents = new LinkedListQueue<>();
  }

  public Queue<FolderOrDocument> getContents() {
	return contents;
  }

  /**
   * Use to check if there is an item in this folder with the given name.
   *
   * @param aName the name to check for
   * @return true iff there is a document or folder with aName in this folder
   */
  public boolean isNameInFolder(String aName) {

	// TODO: Implement me!!!
	return false;
  }

  /**
   * Returns a listing of names (not paths) of all the items in this folder.
   *
   * @return the names of the documents and folders in this folder, sorted by
   * lexicographic order
   */
  public SortedQueue<String> getContentNames() {

	// TODO: Implement me!!!
	return null;
  }

  @Override
  public boolean isFolder() {
	return true;
  }

  // Don't change.... this is used for testing
  /**
   * @return queue of pathnames of all files in the system rooted at the current
   * folder
   */
  public Queue<String> getAllPaths() {
	Queue<String> results = new LinkedListQueue<>();
	results.enqueue(this.getName());
	getAllPaths(getName(), results);
	return results;
  }

  // Don't change.... this is used for testing
  protected void getAllPaths(String prePath, Queue<String> results) {

	SortedQueue<FolderOrDocument> sorted = new LinkedListSortedQueue<>();

	for (int i = 0; i < contents.getSize(); i++) {
	  FolderOrDocument fod;
	  try {
		fod = contents.dequeue();
		contents.enqueue(fod);

		sorted.insert(fod);

	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	}

	while (sorted.getSize() > 0) {
	  try {
		FolderOrDocument fod = sorted.dequeue();
		String fodPath = prePath + "/" + fod.getName();
		results.enqueue(fodPath);
		if (fod.isFolder()) {
		  Folder folder = (Folder) fod;
		  folder.getAllPaths(fodPath, results);
		}
	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}

  }

}
