package io.agileintelligence.projectboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.agileintelligence.projectboard.domain.ProjectTask;
import io.agileintelligence.projectboard.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private ProjectTaskRepository projectTaskRepository; 
	
	public ProjectTask saveorUpdateProjectTask(ProjectTask projectTask) {
		
		if(projectTask.getStatus()==null || projectTask.getStatus()=="")
		{
			projectTask.setStatus("TO_DO");
		}
		return projectTaskRepository.save(projectTask);
	}
	
	public Iterable<ProjectTask> findAll(){
		return projectTaskRepository.findAll();
	}
	
	public ProjectTask findById(Long id) {
		return projectTaskRepository.getById(id);
	}
	
	public void delete(Long id) {
		ProjectTask projectTask = findById(id);
		projectTaskRepository.delete(projectTask);
	}
	
	public ProjectTask updatePT(Long pt_id,ProjectTask projectTask){    
        
        if(projectTaskRepository.findById(pt_id).isPresent()) {
            ProjectTask task = projectTaskRepository.findById(pt_id).get();
            
            task.setAcceptanceCriteria(projectTask.getAcceptanceCriteria());
            task.setSummary(projectTask.getSummary());
            task.setStatus(projectTask.getStatus());
            
            ProjectTask updatedTask = projectTaskRepository.save(task);
            
            return updatedTask;
        }
        
        else {
            return null;
        }
        
    }
	
	

}
