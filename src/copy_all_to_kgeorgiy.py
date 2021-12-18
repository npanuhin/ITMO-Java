from shutil import copyfile, rmtree
import os


ROOT = "../"
KGEORGIY_ROOT = "../../prog-intro/"


def mkpath(*paths):
    return os.path.normpath(os.path.join(*paths))


def removeFromKgeorgiy(path):
    if os.path.isfile(mkpath(KGEORGIY_ROOT, path)):
        os.remove(mkpath(KGEORGIY_ROOT, path))

    elif os.path.isdir(mkpath(KGEORGIY_ROOT, path)):
        rmtree(mkpath(KGEORGIY_ROOT, path))


def ext(path):
    return os.path.splitext(mkpath(path))[1]


def listdir(*paths):
    return os.listdir(mkpath(ROOT, *paths))


def listdir_java(*paths):
    for file in listdir(*paths):
        if ext(file) == ".java":
            yield file


def copy(src, dst):
    print("Copying \"{}\" to \"{}\"".format(src, dst))
    src = mkpath(ROOT, src if isinstance(src, str) else mkpath(*src))
    dst = mkpath(KGEORGIY_ROOT, dst if isinstance(dst, str) else mkpath(*dst))

    if not os.path.splitext(dst)[1].strip():
        dirname = dst
        dst = mkpath(dst, os.path.split(src)[1])
    else:
        dirname = os.path.dirname(dst)

    os.makedirs(dirname, exist_ok=True)

    copyfile(src, dst)


def main():
    removeFromKgeorgiy("java-solutions")
    removeFromKgeorgiy("qf-solutions")

    [copy(mkpath("MyClasses", file), ("java-solutions", "myclasses")) for file in listdir_java("MyClasses")]

    [copy(mkpath("HW2", file), ("java-solutions")) for file in listdir_java("HW2")]

    [copy(mkpath("HW3", file), ("java-solutions")) for file in listdir_java("HW3")]

    [copy(mkpath("HW4", file), ("java-solutions")) for file in listdir_java("HW4")]

    [copy(mkpath("HW5", file), ("java-solutions")) for file in listdir_java("HW5")]

    [copy(mkpath("HW6", file), ("java-solutions")) for file in listdir_java("HW6")]

    [copy(mkpath("HW7", "markup", file), ("java-solutions", "markup")) for file in listdir_java("HW7", "markup")]

    [copy(mkpath("HW8", file), ("qf-solutions", os.path.splitext(file)[0])) for file in listdir_java("HW8")]

    [copy(mkpath("HW9", "md2html", file), ("java-solutions", "md2html")) for file in listdir_java("HW9", "md2html")]

    [copy(mkpath("HW10", "game", file), ("java-solutions", "game")) for file in listdir_java("HW10", "game")]

    [copy(mkpath("HW11", "expression", file), ("java-solutions", "expression")) for file in listdir_java("HW11", "expression")]

    [copy(mkpath("HW12", "expression", "parser", file), ("java-solutions", "expression", "parser")) for file in listdir_java("HW12", "expression", "parser")]

    [copy(mkpath("HW13", "expression", "exceptions", file), ("java-solutions", "expression", "exceptions")) for file in listdir_java("HW13", "expression", "exceptions")]


if __name__ == "__main__":
    main()
