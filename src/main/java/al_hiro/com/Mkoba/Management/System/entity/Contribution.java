package al_hiro.com.Mkoba.Management.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contributions")
public class Contribution extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "amount"  , precision = 11, scale = 2)
    private BigDecimal amount;

    @Column(name = "date_paid")
    private LocalDateTime paymentDate;

    @Column(name = "month")
    private String month; // Month for which the contribution is being made (format: YYYY-MM)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Column(name = "on_time"  , columnDefinition = "BOOLEAN DEFAULT FALSE"  )
    private Boolean onTime;

    @Column(name = "penalty_applied"  , columnDefinition = "BOOLEAN DEFAULT FALSE"  )
    private Boolean penaltyApplied;

    @Column(name = "penalty_amount", precision = 10, scale = 2)
    private BigDecimal penaltyAmount;
}
