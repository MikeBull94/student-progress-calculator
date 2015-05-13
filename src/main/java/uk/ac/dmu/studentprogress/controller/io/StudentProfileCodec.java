package uk.ac.dmu.studentprogress.controller.io;

import com.google.common.collect.ImmutableMap;
import uk.ac.dmu.studentprogress.model.Course;
import uk.ac.dmu.studentprogress.model.MarkType;
import uk.ac.dmu.studentprogress.model.Module;
import uk.ac.dmu.studentprogress.model.ModuleMarks;
import uk.ac.dmu.studentprogress.model.StudentProfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a {@link Codec} that can encode and decode {@link StudentProfile} files.
 */
public final class StudentProfileCodec implements Codec<StudentProfile> {
	@Override
	public StudentProfile decode(String path) throws IOException {
		try (DataInputStream in = new DataInputStream(Files.newInputStream(Paths.get(path)))) {
			String fullName = in.readUTF();
			String pNumber = in.readUTF();
			String courseName = in.readUTF();
			int moduleCount = in.read();

			String[] moduleCodes = new String[moduleCount];
			int[] courseworkMarks = new int[moduleCount];
			int[] examMarks = new int[moduleCount];
			for (int i = 0; i < moduleCount; i++) {
				moduleCodes[i] = in.readUTF();
				courseworkMarks[i] = in.read();
				examMarks[i] = in.read();
			}

			Course course = null;

			for (Course c : Course.values()) {
				if (c.getName().equals(courseName)) {
					course = c;
					break;
				}
			}

			if (course == null) {
				throw new IOException("No course found for the student.");
			}

			Map<Module, ImmutableMap<MarkType, Integer>> marks = new HashMap<>();
			for (int i = 0; i < moduleCount; i++) {
				Module module = Module.fromCode(moduleCodes[i]);
				ImmutableMap.Builder<MarkType, Integer> builder = ImmutableMap.builder();
				builder.put(MarkType.COURSEWORK, courseworkMarks[i]);
				builder.put(MarkType.EXAM, examMarks[i]);
				marks.put(module, builder.build());
			}

			return new StudentProfile(fullName, pNumber, course, new ModuleMarks(ImmutableMap.copyOf(marks)));
		}
	}

	@Override
	public void encode(StudentProfile profile, String path) throws IOException {
		try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(Paths.get(path)))) {
			out.writeUTF(profile.getFullName());
			out.writeUTF(profile.getPNumber());
			out.writeUTF(profile.getCourse().getName());
			Collection<Module> modules = profile.getCourse().getModules();
			out.write(modules.size());
			for (Module module : modules) {
				out.writeUTF(module.getCode());
				out.write(profile.getMark(module, MarkType.COURSEWORK));
				out.write(profile.getMark(module, MarkType.EXAM));
			}
		}
	}
}
