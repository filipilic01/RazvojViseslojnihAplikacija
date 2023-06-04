import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/services/bolnica.service';

@Component({
  selector: 'app-bolnica-dialog',
  templateUrl: './bolnica-dialog.component.html',
  styleUrls: ['./bolnica-dialog.component.css']
})
export class BolnicaDialogComponent {
  public flagBolDialog!: number;//govori nam o tome koja operacija ce se izvrsiti

  constructor(public snackBar: MatSnackBar,//prikazuje poruku
    public bolnicaService: BolnicaService,
    @Inject (MAT_DIALOG_DATA) public data: Bolnica,//podaci dijaloga ce se skladistiti u formi bolnica modela
    public dialogRef: MatDialogRef<BolnicaDialogComponent>){}//referencaa na dijalog

  public add(): void {
      console.log("ID je " + this.data.id + this.data.naziv);
      this.bolnicaService.addBolnica(this.data).subscribe(() => {
        this.snackBar.open('Uspesno dodata bolnica: ' + this.data.naziv, 'OK', {
          duration: 2500
        })
      }),
        (error: Error) => {
          console.log(error.name + ' ' + error.message)
          this.snackBar.open('Doslo je do greske prilikom dodavanja nove bolnice. ', 'Zatvori', {
            duration: 2500
          })
        };
  }
  
  
  public update(): void {
      this.bolnicaService.updateBolnica(this.data).subscribe(() => {
        this.snackBar.open('Uspesno izmenjena bolnica: ' + this.data.naziv, 'OK', {
          duration: 2500
        })
      }),
        (error: Error) => {
          console.log(error.name + ' ' + error.message)
          this.snackBar.open('Doslo je do greske prilikom izmene bolnice. ', 'Zatvori', {
            duration: 2500
          })
        };
  
  }
  
  public delete(): void {
    console.log('Uspesno obrisana sva odeljenja')
      this.bolnicaService.deleteBolnica(this.data.id).subscribe(() => {
        this.snackBar.open('Uspesno obrisana bolnica: ' + this.data.naziv, 'OK', {
          duration: 2500
        }
        )
      }),
        (error: Error) => {
          console.log(error.name + ' ' + error.message)
          this.snackBar.open('Doslo je do greske prilikom brisanja bolnice. ', 'Zatvori', {
            duration: 2500
          })
        };
  }
  
    public cancel(): void {
      this.dialogRef.close();
      this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
        duration: 1000
      })
    }
}
