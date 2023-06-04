import { Pacijent } from '../models/pacijent';
import { PACIJENT_URL, ODELJENJE_PACIJENATA_URL } from '../app.constant';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class PacijentService{

    constructor(private httpClient: HttpClient) { }


    public getAllPacijenti(): Observable<any> {
      return this.httpClient.get(PACIJENT_URL);
  }

    public getAllPacijentiZaOdeljenje(odelId: number): Observable<any> {
        return this.httpClient.get(ODELJENJE_PACIJENATA_URL+'/'+odelId);
    }

    public addPacijent(pacijent: Pacijent): Observable<any> {
        return this.httpClient.post(PACIJENT_URL, pacijent);
      }
    
      public deletePacijent(id: number): Observable<any> {
        return this.httpClient.delete(PACIJENT_URL  + "/" + id);
      }
    
      public updatePacijent(pacijent: Pacijent) : Observable<any>{
        return this.httpClient.put(PACIJENT_URL + "/" + pacijent.id, pacijent);  }
    


}