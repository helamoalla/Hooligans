<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Reponse
 *
 * @ORM\Table(name="reponse")
 * @ORM\Entity
 */
class Reponse
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="message", type="string", length=200, nullable=false)
     * @Assert\NotBlank
     * @Assert\Length(
     *      min = 5,
     *      max = 50,
     *      minMessage = "Your message must be at least {{ limit }} characters long",
     *      maxMessage = "Your message cannot be longer than {{ limit }} characters"
     * )
     * @Assert\Regex(
     *     pattern="/^[A-Z]/",
     *     match=true,
     *     message="Votre message doit commencer par une lettre majuscule"
     * )
     * @Assert\Regex(
     *     pattern = "/[#?!@$%^&*-]+/i",
     *     match=false,
     *     message="Votre message ne doit pas contenir un caractére spéciale" 
     * )
     */
    private $message;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idUser", referencedColumnName="id")
     * })
     */
    private $idUser;

    /**
     * @var \Commentaire
     *
     * @ORM\ManyToOne(targetEntity="Commentaire")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idCommentaire", referencedColumnName="id_comment")
     * })
     */
    private $idCommentaire;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=true)
     * 
     */
    private $date;

    private int $idCom;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMessage(): ?string
    {
        return $this->message;
    }

    public function setMessage(string $message): self
    {
        $this->message = $message;

        return $this;
    }

    public function getIdUser(): ?User
    {
        return $this->idUser;
    }

    public function setIdUser(User $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getIdCommentaire(): ?Commentaire
    {
        return $this->idCommentaire;
    }

    public function setIdCommentaire(?Commentaire $idCommentaire): self
    {
        $this->idCommentaire = $idCommentaire;

        return $this;
    }
    

    public function __toString()
    {
        return $this->id;
    }
    public function getIdCom(): ?int
    {
        return $this->idCom;
    }
    public function setIdCom(int $idCom): ?int
    {
        return $this->idCom=$idCom;
    }
    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }
}
