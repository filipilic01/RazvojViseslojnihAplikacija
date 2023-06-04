import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Pacijent } from 'src/app/models/pacijent';
import { PacijentService } from 'src/app/services/pacijent.service';
import { PacijentDialogComponent} from '../dialogs/pacijent-dialog/pacijent-dialog.component';
import { Odeljenje } from 'src/app/models/odeljenje';


@Component({
  selector: 'app-pacijent',
  templateUrl: './pacijent.component.html',
  styleUrls: ['./pacijent.component.css']
})
export class PacijentComponent {
  displayedColumns = ['id', 'ime', 'prezime', 'zdrOsiguranje', 'datumRodjenja','odeljenje', 'dijagnoza', 'actions']
  dataSourcePacijent!: MatTableDataSource<Pacijent>;
  subscription!: Subscription;
  @Input() selektovanoOdeljenje!: Odeljenje; //omogucavamo komunikaciju izmedju odeljenja i pacijenta

  constructor(private pacijentService: PacijentService,
    private dialog: MatDialog,
    public snackBar: MatSnackBar
  ) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    if (this.selektovanoOdeljenje.id) {
      this.loadData();
    }
  }

  loadData() {

       this.subscription = this.pacijentService.getAllPacijentiZaOdeljenje(this.selektovanoOdeljenje.id)
      .subscribe({
        next: (data) => this.dataSourcePacijent = data,
        error: (error) => {
          this.snackBar.open('Odeljenje nema pacijenata', 'Zatvori', {
            duration: 2500
          }); this.dataSourcePacijent = new MatTableDataSource<Pacijent>
        },
        complete: () => console.info('complete')
      })
    
  }

  public openDialog(flag: number, pacijent?: Pacijent) {
    const dialogRef = this.dialog.open(PacijentDialogComponent, { data: (pacijent ? pacijent : new Pacijent()) });
    dialogRef.componentInstance.flagPacDialog = flag;
    if (flag === 1) {
      dialogRef.componentInstance.data.odeljenje = this.selektovanoOdeljenje;
    }
    dialogRef.afterClosed()
      .subscribe(result => {
        if (result === 1) {
          this.loadData();
        }
      })
  }
}
