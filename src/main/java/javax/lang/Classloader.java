package javax.lang;

import static javax.lang.Try.*;
import static javax.util.List.*;

import java.net.URL;
import java.net.URLClassLoader;

import javax.io.File;
import javax.net.Urls;
import javax.util.List;
import javax.util.jar.JarFile;

public class Classloader extends ClassLoader {

	protected List<File> classpath;
	protected ClassLoader classloader;
	
	public Classloader() {
		this(list());
	}
	
	public Classloader(File... classpath) {
		this.setClasspath(classpath);
	}
	
	public Classloader(List<File> classpath) {
		this.setClasspath(classpath);
	}
	
	public Classloader addPath(File path) {
		return this.setClasspath(this.classpath.append(path));
	}
	
	public Classloader setClasspath(File... classpath) {
		return this.setClasspath(List.list(classpath));
	}
	
	public Classloader setClasspath(List<File> classpath) {
		this.classpath = classpath;
		this.classloader = new URLClassLoader(this.classpath.map(file -> Urls.url(file.toFile().toURI())).toArray(new URL[0]));
		return this;
	}
	
	public java.lang.Class<?> loadClass(String name) throws ClassNotFoundException {
		return this.classloader.loadClass(name);
	}
	
	public List<JarFile> jars() throws Exception {
		return this.classpath.filter(file -> file.exists() && file.name().endsWith("jar")).map(file -> {
			return attempt(() -> new JarFile(file), (JarFile)null);
		}).filter(file -> file != null);
	}
	
	public List<java.lang.Class<?>> classes() throws Exception {
		return list(this.jars().stream().flatMap(jar -> jar.classes(this).stream()));
	}
	
	public List<java.lang.Class<?>> mains() throws Exception {
		return this.classes().filter(clazz -> {
			try {
				return clazz.getMethod("main", String[].class) != null;
			} catch (Exception e) {
				return false;
			}
		});
	}
}