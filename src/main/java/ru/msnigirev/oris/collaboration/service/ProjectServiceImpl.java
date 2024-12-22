package ru.msnigirev.oris.collaboration.service;

import lombok.RequiredArgsConstructor;
import ru.msnigirev.oris.collaboration.entity.Project;
import ru.msnigirev.oris.collaboration.entity.ProjectDto;
import ru.msnigirev.oris.collaboration.entity.User;
import ru.msnigirev.oris.collaboration.repository.impl.InstituteRepository;
import ru.msnigirev.oris.collaboration.repository.impl.ProjectAdminsRepositoryImpl;
import ru.msnigirev.oris.collaboration.repository.impl.SubjectRepository;
import ru.msnigirev.oris.collaboration.repository.impl.TeacherRepository;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectAdminsRepository;
import ru.msnigirev.oris.collaboration.repository.interfaces.ProjectRepository;
import ru.msnigirev.oris.collaboration.repository.interfaces.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final InstituteRepository instituteRepository;
    private final ProjectAdminsRepository projectAdminsRepository;
    @Override
    public Project getById(int id) {
        Project project = projectRepository.getById(id).orElse(null);
        if (project == null) return null;
        Set<User> admins = projectAdminsRepository.getAdmins(project.getId())
                .stream()
                .map(userRepository::getById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
        project.setAdmins(admins);
        return project;
    }

    @Override
    public ProjectDto getDtoById(int id) {
        Project project = getById(id);
        if (project == null) return null;
        /*
        public class ProjectDto {
    private int id;
    private String name;
    private String description;
    private int creatorId;
    private String teacher;
    private String institute;
    private String subject;
    private int year;
    // url + file
    private Map<String, File> projectStructure;
    private Set<User> admins;
         */
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .creatorId(project.getCreatorId())
                .teacher(teacherRepository.getById(project.getTeacherId()))
                .institute(instituteRepository.getById(project.getTeacherId()))
                .subject(subjectRepository.getById(project.getTeacherId()))
                .year(project.getYear())
                .admins(project.getAdmins())
                .build();
    }
}
