import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Note } from './models/note.model';

@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private baseUrl = 'http://localhost:8080/api/notes';

  constructor(private http: HttpClient) {}

  getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(this.baseUrl, { withCredentials: true });
  }

  createNote(note: Note): Observable<any> {
    return this.http.post(this.baseUrl, note, { withCredentials: true });
  }

  updateNote(id: number, note: Note): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, note, { withCredentials: true });
  }

  deleteNote(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { withCredentials: true });
  }
  getNoteById(id: number): Observable<Note>{
    return this.http.get<Note>(`${this.baseUrl}/${id}`, { withCredentials: true });
  }
}
