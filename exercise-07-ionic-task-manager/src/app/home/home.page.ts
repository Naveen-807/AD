import { Component } from '@angular/core';
import { ToastController } from '@ionic/angular';

type Task = {
  id: number;
  text: string;
  done: boolean;
};

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: false,
})
export class HomePage {
  newTask = '';
  tasks: Task[] = [];
  private nextId = 1;

  constructor(private toastCtrl: ToastController) {
    const saved = localStorage.getItem('ex7_tasks');
    if (saved) {
      this.tasks = JSON.parse(saved) as Task[];
      const maxId = this.tasks.reduce((m, t) => Math.max(m, t.id), 0);
      this.nextId = maxId + 1;
    }
  }

  async addTask() {
    const text = this.newTask.trim();
    if (!text) {
      const toast = await this.toastCtrl.create({
        message: 'Enter a task',
        duration: 1200,
        color: 'warning',
      });
      await toast.present();
      return;
    }

    this.tasks = [{ id: this.nextId++, text, done: false }, ...this.tasks];
    this.newTask = '';
    this.save();
  }

  toggleTask(task: Task) {
    task.done = !task.done;
    this.save();
  }

  deleteTask(id: number) {
    this.tasks = this.tasks.filter((t) => t.id !== id);
    this.save();
  }

  trackById(_: number, task: Task) {
    return task.id;
  }

  private save() {
    localStorage.setItem('ex7_tasks', JSON.stringify(this.tasks));
  }
}
