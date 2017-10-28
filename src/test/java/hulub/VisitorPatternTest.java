package hulub;

import java.io.File;

public class VisitorPatternTest {

    interface Visitor {
        void visitFile(File current, int depth);
        void visitDirectory(File current, int depth, File[] contents);
    }

    interface Element {
        void accept(Visitor v, int depth);
    }

    class DirectoryElement implements Element {
        private File f;
        public DirectoryElement(File f) {
            this.f = f;
        }
        public void accept(Visitor v, int depth) {
            v.visitDirectory(f, depth, f.listFiles());
        }
    }

    public class FileElement implements Element {
        private File f;
        public FileElement(File f) {
            this.f = f;
        }
        public void accept(Visitor v, int depth) {
            v.visitFile(f, depth);
        }
    }

    class Directory {

        private File root;

        public Directory(String root) {
            this.root = new File(root);
        }

        public void traverse(Visitor visitor) {
            topDown(root, visitor, 0);
        }

        private void topDown(File root, Visitor visitor, int depth) {
            Element e = root.isFile()
                            ? (new FileElement(root))
                            : (new DirectoryElement(root));
            e.accept(visitor, depth);
            if (!root.isFile()) {
                File[] children = root.listFiles();
                for (int i = 0; i < children.length; ++i)
                    topDown(children[i], visitor, depth + 1);
            }
        }
    }
}
