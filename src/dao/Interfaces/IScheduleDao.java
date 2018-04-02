package dao.Interfaces;

import model.Schedule;

import java.util.List;

/**
 * Created by PANNA on 02.04.2018.
 */
public interface IScheduleDao {
    public List<Schedule> findAll();
    public Schedule findById(Integer scheduleId);
    public boolean create(Schedule schedule);
    public boolean update(Schedule infoForUpdate);
}
